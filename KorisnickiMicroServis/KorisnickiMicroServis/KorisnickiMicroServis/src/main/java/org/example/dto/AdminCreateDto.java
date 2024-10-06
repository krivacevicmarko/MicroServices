package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
public class AdminCreateDto {
    @NotBlank
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    @Email
    private String email;
    @NotBlank
    private LocalDate dateOfBirth;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}
