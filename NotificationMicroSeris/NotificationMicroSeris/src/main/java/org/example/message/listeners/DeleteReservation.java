package org.example.message.listeners;

import org.example.domain.Notification;
import org.example.dto.AccountActivationDto;
import org.example.dto.DeleteReservationDto;
import org.example.dto.ReservationDto;
import org.example.message.MessageHelper;
import org.example.repository.NotificationRepository;
import org.example.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DeleteReservation {


    private MessageHelper messageHelper;
    private NotificationService notificationService;

    public DeleteReservation(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
    }


    @JmsListener(destination = "${destination.deleteReservation}", concurrency = "5-10")
    public void reservationDel(Message message) throws JMSException {
        System.out.println("USAAAAAAAAO");
        DeleteReservationDto deleteReservationDto = messageHelper.getMessage(message, DeleteReservationDto.class);
        String text = "Rezervacija u terminu: "+ deleteReservationDto.getStart() + " - " + deleteReservationDto.getEnd() +  " je otkazana!";
        DeleteReservationDto noviDelete = new DeleteReservationDto(deleteReservationDto.getUsername(),deleteReservationDto.getEmail(),deleteReservationDto.getGymName(), deleteReservationDto.getStart(),deleteReservationDto.getEnd(),text);
        System.out.println(noviDelete.toString());
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        LocalDate noviDate = LocalDate.parse(formattedDate);
        Notification notification = new Notification(noviDelete.getEmail(),text,"DELETE_RESERVATION_MAIL",deleteReservationDto.getUsername(),noviDate);
        notificationService.deleteReservationMethod(noviDelete, notification);
    }



}
