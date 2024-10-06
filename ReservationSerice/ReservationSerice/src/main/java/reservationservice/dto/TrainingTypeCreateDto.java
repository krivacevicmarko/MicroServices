package reservationservice.dto;

import lombok.Getter;
import lombok.Setter;
import reservationservice.domain.Tip;

@Getter
@Setter
public class TrainingTypeCreateDto {
    private String name;
    private double price;
}
