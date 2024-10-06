package org.example.dto;

public class IncrementReservationCountDto {

    public IncrementReservationCountDto() {
    }

    public IncrementReservationCountDto(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
