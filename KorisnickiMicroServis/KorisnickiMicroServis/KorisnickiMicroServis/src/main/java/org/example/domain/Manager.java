package org.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    private String roomName;
    private LocalDate employmentDate;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", employmentDate=" + employmentDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public Manager() {
    }

}
