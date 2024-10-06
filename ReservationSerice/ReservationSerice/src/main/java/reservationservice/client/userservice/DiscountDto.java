package reservationservice.client.userservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountDto {

    private Integer discount;

    public DiscountDto() {
    }

    public DiscountDto(Integer discount) {
        this.discount = discount;
    }

}
