package org.example.message.listeners;

import org.example.domain.Enum;
import org.example.domain.Notification;
import org.example.dto.AccountActivationDto;
import org.example.dto.NotificationDto;
import org.example.message.MessageHelper;
import org.example.repository.NotificationRepository;
import org.example.security.service.implementation.VerificationLinkGenerator;
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
public class AccountActivation {

        private MessageHelper messageHelper;
        private NotificationService notificationService;
        private VerificationLinkGenerator verificationLinkGenerator;

        public AccountActivation(MessageHelper messageHelper, NotificationService notificationService) {
            this.messageHelper = messageHelper;
            this.notificationService = notificationService;
            this.verificationLinkGenerator = new VerificationLinkGenerator();
        }

        @JmsListener(destination = "${destination.accountActivation}", concurrency = "5-10")
        public void accountActivationM(Message message) throws JMSException {
            System.out.println("USAAAAAAAAO");

            AccountActivationDto accountActivationDto = messageHelper.getMessage(message, AccountActivationDto.class);
            System.out.println(accountActivationDto.toString());
            String link = verificationLinkGenerator.generateVerificationLink(accountActivationDto.getUserId());
            String text = "Pozdrav " + accountActivationDto.getName() + " " + accountActivationDto.getSurname() + " da bi ste se verifikovali idite na sledeci link: " + link;
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = date.format(formatter);
            LocalDate noviDate = LocalDate.parse(formattedDate);
            NotificationDto notificationDto = new NotificationDto(accountActivationDto.getEmail(),"ACTIVATION_MAIL",text,accountActivationDto.getName(),noviDate);
            //Ovde se hvataju parametri za notifikaciju koja se potom dodaje u bazu i salje ka mail servicu
            Notification notification = new Notification(notificationDto.getEmail(),notificationDto.getTip(), notificationDto.getText(), notificationDto.getUsername(),noviDate);
            AccountActivationDto activationDtoNovi = new AccountActivationDto(notification.getEmail(), accountActivationDto.getUserId(), text);

            System.out.println(link);
            System.out.println(notificationDto.toString());

            System.out.println(activationDtoNovi.toString());
            notificationService.accountActivationMethod(activationDtoNovi, notification);
        }



}
