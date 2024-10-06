package reservationservice.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double price;
    private Integer userId;
    private Integer gymId;

    public Reservation(Integer userId, Integer gymId, double price) {
        this.userId = userId;
        this.gymId = gymId;
        this.price = price;
    }

    public Reservation() {
    }
}
