package org.example.dto;

import org.example.controller.ManagerController;

public class ManagerRegisterDto {



    private Integer userId;

    public ManagerRegisterDto(){}
    public ManagerRegisterDto(Integer id){
        this.userId = id;
    }

    public Integer getUserId(){return userId;}

    public void setUserId(Integer id){this.userId = id;}


}
