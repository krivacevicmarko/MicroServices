package reservationservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationCreateDto {

    private Integer userId;
    private Integer gymId;



    public ReservationCreateDto(Integer userId, Integer gymId) {
        this.userId = userId;
        this.gymId = gymId;
    }

    @Override
    public String toString(){
        return userId +", " + gymId;
    }
}
