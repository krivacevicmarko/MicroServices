package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class DeleteReservationDto {

    private String username;
    private String email;
    private String gymName;
    private LocalTime start;
    private LocalTime end;
    private String text;

    public DeleteReservationDto(String username, String email, String gymName, LocalTime start, LocalTime end,String text) {
        this.username = username;
        this.email = email;
        this.gymName = gymName;
        this.start = start;
        this.end = end;
        this.text = text;
    }

    @Override
    public String toString() {
        return "DeleteReservationDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gymName='" + gymName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", text=" + text+
                '}';
    }
}
