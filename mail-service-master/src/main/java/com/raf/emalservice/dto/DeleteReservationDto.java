package com.raf.emalservice.dto;




import java.time.LocalTime;

public class DeleteReservationDto {

    private String username;
    private String email;
    private String gymName;
    private LocalTime start;
    private LocalTime end;

    private String text;

    public DeleteReservationDto(String username, String email, String gymName, LocalTime start, LocalTime end) {
        this.username = username;
        this.email = email;
        this.gymName = gymName;
        this.start = start;
        this.end = end;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
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
                '}';
    }
}
