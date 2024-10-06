package com.raf.emalservice.listener;

import com.raf.emalservice.dto.ActivationDto;
import com.raf.emalservice.dto.DeleteReservationDto;
import com.raf.emalservice.dto.ReservationDto;
import com.raf.emalservice.listener.helper.MessageHelper;
import com.raf.emalservice.service.EmailService;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
@Component
public class DeleteReservationListener {


    private MessageHelper messageHelper;
    private EmailService emailService;

    public DeleteReservationListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendDeleteReservationEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        System.out.println("USAO U DELETE");
        DeleteReservationDto deleteReservationDto = messageHelper.getMessage(message, DeleteReservationDto.class);
        System.out.println(deleteReservationDto.toString());
        emailService.sendSimpleMessage("softverskek@gmail.com", "Delete reservation", deleteReservationDto.getText());
    }
}
