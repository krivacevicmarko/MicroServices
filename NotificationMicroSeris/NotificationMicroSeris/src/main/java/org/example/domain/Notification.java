package org.example.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String tip;
    private String text;
    private String username;
    private LocalDate createdDate;



    public Notification() {

    }

    public Notification(String email, String text, String tip, String username, LocalDate createdDate) {
        this.id = id;
        this.email = email;
        this.tip = tip;
        this.text = text;
        this.username = username;
        this.createdDate = createdDate;
    }
}

