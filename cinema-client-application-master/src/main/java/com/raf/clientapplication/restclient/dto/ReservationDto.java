package com.raf.clientapplication.restclient.dto;

public class ReservationDto {

    private Integer userId;
    private Integer gymId;

    public ReservationDto(Integer userId, Integer gymId) {
        this.userId = userId;
        this.gymId = gymId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "userId=" + userId +
                ", gymId=" + gymId +
                '}';
    }
}
