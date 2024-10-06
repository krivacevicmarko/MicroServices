package org.example.dto;

public class ClientRegisterDto {

    private Integer userId;

    public ClientRegisterDto(){}
    public ClientRegisterDto(Integer id){
        this.userId = id;
    }

    public Integer getUserId(){return userId;}

    public void setUserId(Integer id){this.userId = id;}
}
