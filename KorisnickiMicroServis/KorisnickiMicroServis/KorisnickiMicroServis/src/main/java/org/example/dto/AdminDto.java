package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class AdminDto {

    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;

}
