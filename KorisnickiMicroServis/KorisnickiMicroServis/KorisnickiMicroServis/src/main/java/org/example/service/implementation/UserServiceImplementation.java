package org.example.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.domain.Client;
import org.example.domain.ClientStatus;
import org.example.domain.Manager;
import org.example.dto.*;
import org.example.exception.NotFoundException;
import org.example.listener.MessageHelper;
import org.example.mapper.ClientMapper;
import org.example.mapper.ManagerMapper;
import org.example.repository.ClientRepository;
import org.example.repository.ClientStatusRepository;
import org.example.repository.ManagerRepository;
import org.example.security.service.TokenService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

    private TokenService tokenService;
    private  ClientRepository clientRepository;
    private ManagerRepository managerRepository;

    private ClientStatusRepository clientStatusRepository;
    private ClientMapper clientMapper;
    private ManagerMapper managerMapper;
    private String accountActivationDestination;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private ClientRegisterDto clientRegisterDto;
    private ManagerRegisterDto managerRegisterDto;

    public UserServiceImplementation() {
    }

    @Autowired
    public UserServiceImplementation(ClientRepository clientRepository, ClientStatusRepository clientStatusRepository, ClientMapper clientMapper, ManagerRepository managerRepository, ManagerMapper managerMapper,
                                     JmsTemplate jmsTemplate, MessageHelper messageHelper,@Value("${destination.accountActivation}")String accountActivationDestination, TokenService tokenService){
        this.clientRepository = clientRepository;
        this.clientStatusRepository = clientStatusRepository;
        this.clientMapper = clientMapper;
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.accountActivationDestination = accountActivationDestination;
        this.tokenService = tokenService;
    }

    @Override
    public Page<ClientDto> findAllClients(Pageable pageable) {

        return clientRepository.findAll(pageable).map(clientMapper::clientToClientDto);
    }

    @Override
    public void blockClient(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        client.setBlocked(true);
        clientRepository.save(client);
    }

    @Override
    public void unblockClient(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        client.setBlocked(false);
        clientRepository.save(client);
    }

    @Override
    public void editClientProfile(Integer clientId,int numberOfTrainings, String username, String password, String email, LocalDate dataOfBirth, String name, String surname,boolean isBlocked) {


        Client user = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Update user profile information based on the provided DT
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setDateOfBirth(dataOfBirth);
        user.isBlocked();
        // ... update other fields as needed

        // Save the updated user back to the database
        clientRepository.save(user);
    }


    @Override
    public Page<AdminDto> findAllAdmins(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ManagerDto> findAllManagers(Pageable pageable) {
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto){
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        System.out.println(client.getId());

        jmsTemplate.convertAndSend(accountActivationDestination,messageHelper.createTextMessage(client));
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public AdminDto add(AdminCreateDto adminCreateDto) {
        return null;
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto){
        Manager manager = managerMapper.managerCreateDtoUser(managerCreateDto);
        jmsTemplate.convertAndSend(accountActivationDestination,messageHelper.createTextMessage(manager));
        managerRepository.save(manager);
        System.out.println(manager);
        return managerMapper.managerToManagerDto(manager);

    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {


        Client client = clientRepository
                .findClientByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        if(client.isBlocked()){
           return null;
        }
        Claims claims = Jwts.claims();
        claims.put("id", client.getId());

        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public TokenResponseDto loginManager(TokenRequestDto tokenRequestDto) {
        Manager client = managerRepository
                .findManagerByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        //Create token payload

        Claims claims = Jwts.claims();
        claims.put("id", client.getId());

        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ClientDto findClientById(Integer id) {
        return clientRepository.findById(id)
                .map(clientMapper::clientToClientDto)
                .orElseThrow(()->new NotFoundException(String.format("Gym with id: %d does not exists.", id)));
    }

    @Override
    public ClientDto findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email)
                .map(clientMapper::clientToClientDto)
                .orElseThrow(()->new NotFoundException(String.format("Gym with email: %d does not exists.", email)));
    }

    @Override
    public ManagerDto findManagerByEmail(String email) {

        return managerRepository.findManagerByEmail(email)
                .map(managerMapper::managerToManagerDto)
                .orElseThrow(()->new NotFoundException(String.format("Gym with email: %d does not exists.", email)));
    }

    @Override
    public ManagerDto findManagerById(Integer id) {
        return managerRepository.findById(id)
                .map(managerMapper::managerToManagerDto)
                .orElseThrow(()->new NotFoundException(String.format("Manager with id: %d does not exists.", id)));
    }

    @Override
    public void incrementReservationCount(IncrementReservationCountDto incrementReservationCountDto) {
        clientRepository.findById(incrementReservationCountDto.getUserId())
                .ifPresent(user1->{
                    user1.setNumberOfReservations(user1.getNumberOfReservations() + 1);
                    clientRepository.save(user1);
                });
    }

    @Override
    public DiscountDto findDiscount(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("User with id: %d not found",id)));
        List<ClientStatus> clientStatusList = clientStatusRepository.findAll();
        Integer discount = clientStatusList.stream()
                .filter(clientStatus -> clientStatus.getMaxNumberOfReservations()>=client.getNumberOfReservations()
                        && clientStatus.getMinNumberOfReservations() <= client.getNumberOfReservations())
                .findAny()
                .get()
                .getDiscount();
        return new DiscountDto(discount);
    }
}
