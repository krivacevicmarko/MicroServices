package com.raf.emalservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.raf.emalservice.listener.ReservationListener;

public class ReservationDto {

    private Integer userId;
    private Integer gymId;
    private String text;

    @JsonCreator
    public ReservationDto(Integer userId, String text, Integer gymId){
        this.userId = userId;
        this.text = text;
        this.gymId = gymId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ActivationDto{" +
                "userId=" + userId +
                ", text='" + text + '\'' +
                ", gymId='" + gymId + '\'' +
                '}';
    }
}
