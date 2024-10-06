package com.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class NotificationListDto {

    private List<NotificationDto> content = new ArrayList<>();

    public NotificationListDto() {
        System.out.println(content.toString());
    }

    public NotificationListDto(List<NotificationDto> content) {
        this.content = content;
    }

    public List<NotificationDto> getContent() {
        return content;
    }

    public void setContent(List<NotificationDto> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NotificationListDto{" +
                "content=" + content +
                '}';
    }
}
