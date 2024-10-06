package reservationservice.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reservationservice.domain.Reservation;
import reservationservice.dto.*;
import reservationservice.exception.NotFoundException;
import reservationservice.listener.helper.MessageHelper;
import reservationservice.mapper.ReservationMapper;
import reservationservice.repository.GymRepository;
import reservationservice.repository.ReservationRepository;
import reservationservice.repository.TrainingRepository;
import reservationservice.service.ReservationService;
import reservationservice.service.TrainingService;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private TrainingRepository trainingRepository;
    private ReservationMapper reservationMapper;
    private RestTemplate userServiceRestTemplate;
    private RestTemplate gymServiceRestTemplate;
    private JmsTemplate jmsTemplate;
    private String incrementReservationCountDestination;
    private String reservationNotificationDestination;
    private String deleteNotificationDestination;
    private MessageHelper messageHelper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, TrainingRepository trainingRepository, ReservationMapper reservationMapper, RestTemplate userServiceRestTemplate, RestTemplate gymServiceRestTemplate, JmsTemplate jmsTemplate,
                                   @Value("${destination.deleteReservation}") String deleteNotificationDestination, @Value("${destination.reservationNotification}") String reservationNotificationDestination , @Value("${destination.incrementReservationCount}") String incrementReservationCountDestination, MessageHelper messageHelper) {
        this.reservationRepository = reservationRepository;
        this.trainingRepository = trainingRepository;
        this.gymServiceRestTemplate = gymServiceRestTemplate;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.reservationMapper = reservationMapper;
        this.jmsTemplate = jmsTemplate;
        this.incrementReservationCountDestination = incrementReservationCountDestination;
        this.reservationNotificationDestination = reservationNotificationDestination;
        this.deleteNotificationDestination = deleteNotificationDestination;
        this.messageHelper = messageHelper;
    }

    @Override
    public void addReservation(ReservationCreateDto reservationCreateDto) {
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        ResponseEntity<GymDto> gymDtoResponseEntity = null;

        try {
            // Fetch user details
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/"
                    + reservationCreateDto.getGymId(), HttpMethod.GET, null, ClientDto.class);
            // Fetch gym details
            gymDtoResponseEntity = gymServiceRestTemplate.exchange("/gym/"
                    + reservationCreateDto.getGymId(), HttpMethod.GET, null, GymDto.class);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Projection with id: %d not found.", reservationCreateDto.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClientDto clientDto = clientDtoResponseEntity.getBody();
        GymDto gymDto = gymDtoResponseEntity.getBody();
        System.out.println(gymDto.toString());
        System.out.println(clientDto.toString());
        if(gymDto.getTraining().getCapacity() != 0){
            trainingRepository.findById(gymDto.getTraining().getId()).ifPresent(gym1->{
                gym1.setCapacity(gym1.getCapacity()-1);
                trainingRepository.save(gym1);
            });
            ResponseEntity<DiscountDto> discountDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
                    reservationCreateDto.getUserId() + "/discount",HttpMethod.GET,null, DiscountDto.class);
            Double price = ((gymDtoResponseEntity.getBody().getTraining().getTrainingTypeDto().getPrice())/100)
                    *(100-discountDtoResponseEntity.getBody().getDiscount());
            Reservation reservation = new Reservation(reservationCreateDto.getUserId(), reservationCreateDto.getGymId(),price);
            reservationRepository.save(reservation);
            jmsTemplate.convertAndSend(incrementReservationCountDestination,messageHelper.createTextMessage(new IncrementReservationCountDto(reservationCreateDto.getUserId())));
            ReservationNotificationDto reservationNotificationDto = new ReservationNotificationDto(clientDto.getEmail(),gymDto.getName(), gymDto.getTraining().getKraj(), gymDto.getTraining().getPocetak(), clientDto.getUsername());
            jmsTemplate.convertAndSend(reservationNotificationDestination,messageHelper.createTextMessage(reservationNotificationDto));
            System.out.println(reservationNotificationDto.toString());
            System.out.println(messageHelper.createTextMessage(new IncrementReservationCountDto(reservationCreateDto.getUserId())));
        }else{
            System.out.println("Popunjeno je!");
        }



        //save reservation
        //ide provera da li korisnik dodaje registraciju u dobro vreme -> provera iz proslog projekta
        //i mora da se pazi da li je tip treninga grupni ako jeste onda se vrsi provera if gym_id.getTraining.getCapacity <=12
        //ukoliko je personalni onda se proverava da nije >1 tjt
        //mozemo da dodamo i da se prosledjuje i cena treninga zbog discount-a posle


    }

    @Override
    public void deleteReservation(Integer id) {
        ResponseEntity<ReservationDto> reservationDtoResponseEntity = null;
        try{
            // Fetch user details
            reservationDtoResponseEntity = gymServiceRestTemplate.exchange("/reservation/"
                    + id, HttpMethod.GET, null, ReservationDto.class);
        }catch(Exception e){
            e.printStackTrace();
        }



        ReservationDto reservationDto = reservationDtoResponseEntity.getBody();

        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        ResponseEntity<GymDto> gymDtoResponseEntity = null;

        try {
            // Fetch user details
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/"
                    + reservationDto.getGymId(), HttpMethod.GET, null, ClientDto.class);
            // Fetch gym details
            gymDtoResponseEntity = gymServiceRestTemplate.exchange("/gym/"
                    + reservationDto.getGymId(), HttpMethod.GET, null, GymDto.class);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Projection with id: %d not found.", reservationDto.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClientDto clientDto = clientDtoResponseEntity.getBody();
        GymDto gymDto = gymDtoResponseEntity.getBody();
        System.out.println(reservationDto.toString());
        System.out.println(clientDto);
        System.out.println(gymDto);
        DeleteReservationDto deleteReservationDto = new DeleteReservationDto(clientDto.getUsername(), clientDto.getEmail(),gymDto.getName(),gymDto.getTraining().getPocetak(), gymDto.getTraining().getKraj());
        jmsTemplate.convertAndSend(deleteNotificationDestination,messageHelper.createTextMessage(deleteReservationDto));
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDto findById(Integer id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::trainingToTrainingDto)
                .orElseThrow(()->new NotFoundException(String.format("Training with id: %d does not exists.", id)));
    }
}
