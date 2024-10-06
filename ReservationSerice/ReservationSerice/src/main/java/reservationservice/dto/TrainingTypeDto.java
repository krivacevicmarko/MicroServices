package reservationservice.dto;

import lombok.Getter;
import lombok.Setter;
import reservationservice.domain.Tip;

@Getter
@Setter
public class TrainingTypeDto {

    private Integer id;

    private String name;
    private double price;

    @Override
    public String toString() {
        return "TrainingTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
