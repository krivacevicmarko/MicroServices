package com.raf.emalservice.listener;

import com.raf.emalservice.dto.ActivationDto;
import com.raf.emalservice.listener.helper.MessageHelper;
import com.raf.emalservice.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public EmailListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendActivationEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        System.out.println("USAO U ACTIVATION");
      ActivationDto activationDto = messageHelper.getMessage(message, ActivationDto.class);
        System.out.println(activationDto.toString());
        emailService.sendSimpleMessage("softverskek@gmail.com", "Email activation", activationDto.getText());
    }
}
