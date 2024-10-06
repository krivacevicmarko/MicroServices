package org.example.service;

import org.example.domain.Notification;
import org.example.dto.*;
import org.example.exception.NotFoundException;
import org.example.mapper.NotificationMapper;
import org.example.message.MessageHelper;
import org.example.repository.NotificationRepository;
import org.example.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class NotificationServiceImplementation implements NotificationService{


    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;

    private String accountActivationDestination;
    private String reservationDestination;
    private String deleteReservationDestination;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private TokenService tokenService;


    @Autowired
    public NotificationServiceImplementation(NotificationRepository notificationRepository, NotificationMapper notificationMapper,  TokenService tokenService, JmsTemplate jmsTemplate,
                                           @Value("${destination.sendDeleteReservationEmails}") String deleteReservationDestination, @Value("${destination.sendReservationEmails}")String reservationDestination, @Value("${destination.sendActivationEmails}") String accountActivationDestination, MessageHelper messageHelper){
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.accountActivationDestination = accountActivationDestination;
        this.reservationDestination = reservationDestination;
        this.deleteReservationDestination = deleteReservationDestination;
        this.messageHelper=messageHelper;
        this.jmsTemplate = jmsTemplate;
        this.tokenService = tokenService;
    }



    @Override
    public NotificationDto createNotification(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationCreateDto(notificationCreateDto);
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public Page<NotificationDto> findAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable).map(notificationMapper::notificationToNotificationDto);

    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationToNotificationDto);
    }


    @Override
    public NotificationDto findNotificationById(Integer id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::notificationToNotificationDto)
                .orElseThrow(()->new NotFoundException(String.format("Gym with id: %d does not exists.", id)));
    }

    @Override
    public List<NotificationDto> findByType(String tip) {
        List<Notification> optionalTraining = notificationRepository.findByTip(tip);
        List<Notification> trainings = optionalTraining;
        return trainings.stream()
                .map(notificationMapper::notificationToNotificationDto)
                .collect(Collectors.toList());
    }


    @Override
    public void accountActivationMethod(AccountActivationDto accountActivationDto, Notification notification) {
        notificationRepository.save(notification);
        System.out.println(accountActivationDto.getText());
        jmsTemplate.convertAndSend(accountActivationDestination,messageHelper.createTextMessage(accountActivationDto));

    }

    @Override
    public void reservationMethod(ReservationDto reservationDto, Notification notification) {
        notificationRepository.save(notification);
        System.out.println(reservationDto.getText());
        jmsTemplate.convertAndSend(reservationDestination,messageHelper.createTextMessage(reservationDto));
    }

    @Override
    public void deleteReservationMethod(DeleteReservationDto deleteReservationDto, Notification notification) {
        notificationRepository.save(notification);
        jmsTemplate.convertAndSend(deleteReservationDestination,messageHelper.createTextMessage(deleteReservationDto));
    }
}
