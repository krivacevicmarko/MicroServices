package reservationservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {
    private Integer id;
    private Integer userId;
    private Integer GymId;


    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", GymId=" + GymId +
                '}';
    }
}
