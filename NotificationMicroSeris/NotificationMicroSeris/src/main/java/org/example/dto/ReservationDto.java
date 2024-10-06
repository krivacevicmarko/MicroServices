package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
public class ReservationDto {

    private String email;
    private String username;
    private String gymName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String text;


    public ReservationDto(String email,String gymName, LocalTime startTime, LocalTime endTime, String username,String text) {
        this.email = email;
        this.gymName = gymName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.username = username;
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReservationNotificationDto{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", gymName='" + gymName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

}
