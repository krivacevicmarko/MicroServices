package org.example.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
public class ManagerCreateDto {

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
    @NotBlank
    private String roomName;
    @NotNull
    private LocalDate employmentDate;

    @Override
    public String toString() {
        return username + " " + password + " " + email + " " + dateOfBirth + " " + name + " " + surname + " " + roomName + " " + employmentDate;
    }

}
