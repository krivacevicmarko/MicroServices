package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Admin{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;

    public Admin() {
    }
}
