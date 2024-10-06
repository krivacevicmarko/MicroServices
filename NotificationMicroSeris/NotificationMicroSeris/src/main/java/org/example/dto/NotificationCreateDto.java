package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class NotificationCreateDto {

    @Email
    private String email;
    @NotBlank
    private String tip;
    @NotBlank
    private String text;
    @NotBlank
    private String username;
    @NotNull
    private LocalDate changedDate;
}
