package org.example.listener;

import org.example.dto.IncrementReservationCountDto;
import org.example.service.UserService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class IncrementReservationCountListener {
    private MessageHelper messageHelper;
    private UserService userService;

    public IncrementReservationCountListener(MessageHelper messageHelper, UserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.incrementReservationCount}", concurrency = "5-10")
    public void incrementReservationCount(Message message) throws JMSException {
        System.out.println("USAAAAAAAAO");
        IncrementReservationCountDto incrementReservationCountDto = messageHelper.getMessage(message, IncrementReservationCountDto.class);
        System.out.println(incrementReservationCountDto);
        userService.incrementReservationCount(incrementReservationCountDto);
    }

}
