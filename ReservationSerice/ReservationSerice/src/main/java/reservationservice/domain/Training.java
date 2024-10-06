package reservationservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;

@Entity
@Getter
@Setter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate datum;
    private String dan;
    private LocalTime pocetak;
    private LocalTime kraj;
    @ManyToOne
    private TrainingType trainingType;
    private Tip tip;
    private String trener;
    private int capacity;

}
