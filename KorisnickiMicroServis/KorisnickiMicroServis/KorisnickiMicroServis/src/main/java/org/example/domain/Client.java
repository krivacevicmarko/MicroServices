package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;
    private Integer numberOfReservations;
    private boolean isBlocked;

    public Client(){
        super();
    }

}
