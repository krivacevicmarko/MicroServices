package com.raf.emalservice.listener;

import com.raf.emalservice.dto.ActivationDto;
import com.raf.emalservice.dto.ReservationDto;
import com.raf.emalservice.listener.helper.MessageHelper;
import com.raf.emalservice.service.EmailService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
@Component
public class ReservationListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public ReservationListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendReservationEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        System.out.println("USAO U RESERVATION");
        ReservationDto reservationDto = messageHelper.getMessage(message, ReservationDto.class);
        System.out.println(reservationDto.toString());
        emailService.sendSimpleMessage("softverskek@gmail.com", "Reservation", reservationDto.getText());
        emailService.sendSimpleMessage("mkrivacevic11921rn@raf.rs", "Reservation", reservationDto.getText());
    }
}
