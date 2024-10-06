package org.example.service;

import org.example.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface UserService {

    Page<ClientDto> findAllClients(Pageable pageable);

    void blockClient(Integer clientId);
    void unblockClient(Integer clientId);
    void editClientProfile(Integer clientId,int numberOfTrainings,String username, String password, String email, LocalDate dataOfBirth, String name, String surname,boolean isBlocked);
    Page<AdminDto> findAllAdmins(Pageable pageable);
    Page<ManagerDto> findAllManagers(Pageable pageable);
    ClientDto findClientById(Integer id);
    ClientDto findClientByEmail(String email);
    ManagerDto findManagerByEmail(String email);
    ManagerDto findManagerById(Integer id);
    ClientDto add(ClientCreateDto userCreateDto);
    AdminDto add(AdminCreateDto adminCreateDto);
    ManagerDto add(ManagerCreateDto managerCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    TokenResponseDto loginManager(TokenRequestDto tokenRequestDto);
    void incrementReservationCount(IncrementReservationCountDto incrementReservationCountDto);
    DiscountDto findDiscount(Integer id);
}
