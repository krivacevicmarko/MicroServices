package reservationservice.mapper;

import org.springframework.stereotype.Component;
import reservationservice.domain.Reservation;
import reservationservice.domain.Training;
import reservationservice.dto.ReservationCreateDto;
import reservationservice.dto.ReservationDto;
import reservationservice.dto.TrainingCreateDto;
import reservationservice.dto.TrainingDto;
import reservationservice.exception.NotFoundException;
import reservationservice.repository.TrainingTypeRepository;
@Component
public class ReservationMapper {

    private TrainingTypeRepository trainingTypeRepository;
    private TrainingTypeMapper trainingTypeMapper;

    public ReservationMapper(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper){
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    public ReservationDto trainingToTrainingDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUserId());
        reservationDto.setGymId(reservation.getGymId());
        return reservationDto;
    }

    public Reservation reservationCreateDtoToReservation(ReservationCreateDto reservationCreateDto){
       Reservation reservation = new Reservation();
       reservation.setGymId(reservationCreateDto.getGymId());
       reservation.setUserId(reservationCreateDto.getUserId());
       return reservation;
    }

}
