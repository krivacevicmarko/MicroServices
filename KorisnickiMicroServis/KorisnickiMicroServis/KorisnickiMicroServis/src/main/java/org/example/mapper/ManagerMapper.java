package org.example.mapper;

import org.example.domain.Manager;
import org.example.dto.ManagerCreateDto;
import org.example.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    public ManagerDto managerToManagerDto(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setUsername(manager.getUsername());
        managerDto.setPassword(manager.getPassword());
        managerDto.setEmail(manager.getEmail());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setName(manager.getName());
        managerDto.setSurname(manager.getSurname());
        managerDto.setRoomName(manager.getRoomName());
        managerDto.setEmploymentDate(manager.getEmploymentDate());
        return managerDto;
    }


    public Manager managerCreateDtoUser(ManagerCreateDto managerCreateDto){
        Manager manager = new Manager();
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
        manager.setName(managerCreateDto.getName());
        manager.setSurname(managerCreateDto.getSurname());
        manager.setRoomName(managerCreateDto.getRoomName());
        manager.setEmploymentDate(managerCreateDto.getEmploymentDate());
        return manager;
    }

    public ManagerMapper() {
    }
}
