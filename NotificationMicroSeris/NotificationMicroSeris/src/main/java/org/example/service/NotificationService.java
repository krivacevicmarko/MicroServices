package org.example.service;

import org.example.domain.Notification;
import org.example.dto.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {




    NotificationDto createNotification(NotificationCreateDto notification);

    Page<NotificationDto> findAllNotifications(Pageable pageable);

    Page<NotificationDto> findAll(Pageable pageable);
    NotificationDto findNotificationById(Integer id);

    List<NotificationDto> findByType(String tip);


    void accountActivationMethod(AccountActivationDto accountActivationDto, Notification notification);

    void reservationMethod(ReservationDto reservationDto, Notification notification);

    void deleteReservationMethod(DeleteReservationDto deleteReservationDto, Notification notification);

}