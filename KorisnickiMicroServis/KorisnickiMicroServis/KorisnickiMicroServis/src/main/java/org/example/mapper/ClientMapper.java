package org.example.mapper;

import org.example.domain.Client;
import org.example.dto.ClientCreateDto;
import org.example.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientMapper(){};

    public ClientDto clientToClientDto(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setUsername(client.getUsername());
        clientDto.setPassword(client.getPassword());
        clientDto.setEmail(client.getEmail());
        clientDto.setDateOfBirth(client.getDateOfBirth());
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setNumberOfReservations(client.getNumberOfReservations());
        client.setBlocked(client.isBlocked());
        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto){
        Client client = new Client();
        client.setName(clientCreateDto.getName());
        client.setSurname(clientCreateDto.getSurname());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setEmail(clientCreateDto.getEmail());
        client.setDateOfBirth(clientCreateDto.getDateOfBirth());
        client.setNumberOfReservations(clientCreateDto.getNumberOfReservations());
        client.setBlocked(clientCreateDto.isBlocked());
        return client;
    }

}
