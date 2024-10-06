package org.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ClientStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer minNumberOfReservations;
    private Integer maxNumberOfReservations;
    private Integer discount;

    public ClientStatus(Integer minNumberOfReservations, Integer maxNumberOfReservations, Integer discount) {
        this.minNumberOfReservations = minNumberOfReservations;
        this.maxNumberOfReservations = maxNumberOfReservations;
        this.discount = discount;
    }


    public ClientStatus(){}

}
