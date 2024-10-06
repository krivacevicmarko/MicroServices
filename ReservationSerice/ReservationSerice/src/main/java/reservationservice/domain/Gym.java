package reservationservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String shortDescription;
    private int numberOfPersonalTrainers;
    @ManyToOne
    private Training training;

    public Gym() {
    }

    //    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
//    private List<IndiidualTraining> individualTrainings;
//
//    @OneToMany(mappedBy = "gym",cascade = CascadeType.ALL)
//    private List<GroupeTraining> groupeTrainings;

}
