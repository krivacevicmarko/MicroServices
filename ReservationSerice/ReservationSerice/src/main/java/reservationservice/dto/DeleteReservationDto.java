package reservationservice.dto;

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

    public DeleteReservationDto(String username, String email, String gymName, LocalTime start, LocalTime end) {
        this.username = username;
        this.email = email;
        this.gymName = gymName;
        this.start = start;
        this.end = end;
    }


}
