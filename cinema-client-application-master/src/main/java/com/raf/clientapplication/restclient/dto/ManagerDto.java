package com.raf.clientapplication.restclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDto {

    private Integer id;
    private String roomName;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String employmentDate;
    private String name ;
    private String surname;

    public ManagerDto() {
    }

    public ManagerDto(Integer id, String roomName, String username, String password, String email, String dateOfBirth, String employmentDate, String name, String surname) {
        this.id = id;
        this.roomName = roomName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.employmentDate = employmentDate;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "ManagerDto{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", employmentDate='" + employmentDate + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

