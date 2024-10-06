package org.example.mapper;

import org.example.domain.Enum;
import org.example.domain.Notification;
import org.example.dto.NotificationCreateDto;
import org.example.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class NotificationMapper {
    public NotificationMapper() {
    }

    public NotificationDto notificationToNotificationDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmail(notification.getEmail());
        notificationDto.setTip(notification.getTip());
        notificationDto.setText(notification.getText());
        notificationDto.setUsername(notification.getUsername());
        notificationDto.setCreatedDate(notification.getCreatedDate());
        return notificationDto;
    }

    public Notification notificationCreateDto(NotificationCreateDto notificationCreateDto){
        Notification notification = new Notification();
        notification.setEmail(notificationCreateDto.getEmail());
        notification.setTip( notificationCreateDto.getTip());
        notification.setText(notificationCreateDto.getText());
        notification.setUsername(notificationCreateDto.getUsername());
        notification.setCreatedDate(notificationCreateDto.getChangedDate());
        return notification;
    }


}
