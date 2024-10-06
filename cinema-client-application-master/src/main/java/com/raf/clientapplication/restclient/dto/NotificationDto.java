package com.raf.clientapplication.restclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {

    private String email;
    private String tip;
    private String text;
    private String username;

    public NotificationDto() {
    }

    public NotificationDto( String email, String type, String text, String username) {
        this.email = email;
        this.tip = type;
        this.text = text;
        this.username = username;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "email='" + email + '\'' +
                ", type='" + tip + '\'' +
                ", text='" + text + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
