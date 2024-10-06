package reservationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
public class ReservationNotificationDto {

    private String email;
    private String username;
    private String gymName;
    private LocalTime startTime;
    private LocalTime endTime;


    public ReservationNotificationDto(String email,String gymName, LocalTime startTime, LocalTime endTime, String username) {
        this.email = email;
        this.gymName = gymName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.username = username;
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
