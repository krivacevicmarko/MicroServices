package com.raf.emalservice.dto;


import com.fasterxml.jackson.annotation.JsonCreator;

public class ActivationDto {


    private Integer userId;
    private String text;
    private String email;
    @JsonCreator
    public ActivationDto(Integer userId, String text, String email){
        this.userId = userId;
        this.text = text;
        this.email = email;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                '}';
    }
}
