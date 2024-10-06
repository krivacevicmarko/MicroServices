package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
public class ManagerDto {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private LocalDate dateOfBirth;

    private String name;

    private String surname;

    private String roomName;

    private LocalDate employmentDate;


}
