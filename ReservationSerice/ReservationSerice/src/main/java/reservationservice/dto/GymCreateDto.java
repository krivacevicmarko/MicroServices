package reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import reservationservice.domain.Training;

@Getter
@Setter
public class GymCreateDto {
    private String name;
    private String shortDescription;
    private int numberOfPersonalTrainers;
    @JsonProperty("training")
    private Integer trainingId;
}
