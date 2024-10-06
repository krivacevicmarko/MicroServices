package reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GymDto {
    private Integer id;

    private String name;
    private String shortDescription;
    private int numberOfPersonalTrainers;
    @JsonProperty("training")
    private TrainingDto training;


    @Override
    public String toString() {
        return "GymDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", numberOfPersonalTrainers=" + numberOfPersonalTrainers +
                ", training=" + training +
                '}';
    }
}
