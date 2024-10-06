package org.example.dto;

public class AccountActivationDto {

    private Integer userId;
    private String name;
    private String surname;
    private String email;
    private String tip;
    private String text;


    public AccountActivationDto(String email, Integer id,String text){
        this.email = email;
        this.userId = id;
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setText(String tip) {
        this.text= text;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return "AccountActivationDto{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", text = " + text + '\'' +
                 '}';
    }
}
