package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ClientDto {

    private Integer id;
    private int numberOfReservations;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;
    private boolean blocked;

}
