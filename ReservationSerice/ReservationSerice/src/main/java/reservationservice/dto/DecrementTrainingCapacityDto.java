package reservationservice.dto;

public class DecrementTrainingCapacityDto {

    private Integer gymId;

    public DecrementTrainingCapacityDto(Integer gymId) {
        this.gymId = gymId;
    }

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }
}
