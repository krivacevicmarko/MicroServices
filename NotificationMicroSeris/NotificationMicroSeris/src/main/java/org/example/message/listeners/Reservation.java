package org.example.message.listeners;

import org.example.domain.Notification;
import org.example.dto.AccountActivationDto;
import org.example.dto.NotificationDto;
import org.example.dto.ReservationDto;
import org.example.message.MessageHelper;
import org.example.repository.NotificationRepository;
import org.example.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class Reservation {


    private MessageHelper messageHelper;
    private NotificationService notificationService;
    private NotificationRepository notificationRepository;

    public Reservation(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
    }


    @JmsListener(destination = "${destination.reservationNotification}", concurrency = "5-10")
    public void reservationM(Message message) throws JMSException {
        System.out.println("USAAAAAAAAO");

        ReservationDto reservationDto = messageHelper.getMessage(message, ReservationDto.class);
        String text = "Pozdrav " + reservationDto.getUsername() + " Rezervisali ste trening u sali " + reservationDto.getGymName() + " u terminu : " +
                reservationDto.getStartTime() + " - " + reservationDto.getEndTime();
        ReservationDto newReservation = new ReservationDto(reservationDto.getEmail(), reservationDto.getGymName(), reservationDto.getStartTime(), reservationDto.getEndTime(), reservationDto.getUsername(),text);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        LocalDate noviDate = LocalDate.parse(formattedDate);
        Notification notification = new Notification(reservationDto.getEmail(),text, "RESERVATION_MAIL",reservationDto.getUsername(),noviDate);
        notificationService.reservationMethod(newReservation, notification);
    }



}

