package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ClientCreateDto {

    @NotNull
    private int numberOfReservations;
    @NotBlank
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    @Email
    private String email;
    @NotNull
    private LocalDate dateOfBirth;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private boolean blocked;

}
