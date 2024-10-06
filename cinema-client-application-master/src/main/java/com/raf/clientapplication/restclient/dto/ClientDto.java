package com.raf.clientapplication.restclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class ClientDto {

    private Integer id;
    private int numberOfReservations;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String name ;
    private String surname;
    private boolean isBlocked;


    public ClientDto() {
    }

    public ClientDto(int numberOfReservations, String username, String password, String email, String dateOfBirth, String name, String surname, boolean isBlocked) {
        this.numberOfReservations = numberOfReservations;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.surname = surname;
        this.isBlocked = isBlocked;
    }

    public ClientDto(Integer id, int numberOfReservations, String username, String password, String email, String dateOfBirth, String name, String surname, boolean isBlocked) {
        this.id = id;
        this.numberOfReservations = numberOfReservations;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.surname = surname;
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", numberOfReservations=" + numberOfReservations +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
