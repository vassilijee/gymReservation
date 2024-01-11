package com.projekat2.NotificationService.listeners;

import com.projekat2.NotificationService.dto.emailDto.EmailDto;
import com.projekat2.NotificationService.dto.emailDto.EmailNewSessionDto;
import com.projekat2.NotificationService.listeners.helper.MessageHelper;
import com.projekat2.NotificationService.mapper.NotificationTypeMapper;
import com.projekat2.NotificationService.service.EmailService;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;

public class SuccessfulReservationNotificationListener {
    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationTypeMapper notificationTypeMapper;

    public SuccessfulReservationNotificationListener(MessageHelper messageHelper, EmailService emailService, NotificationTypeMapper notificationTypeMapper) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @JmsListener(destination = "${destination.reservation}", concurrency = "5-10")
    public void reservationNotificationListener(Message message) throws JMSException {
        EmailNewSessionDto reservationEmailDto = messageHelper.getMessage(message, EmailNewSessionDto.class);
        System.out.println(reservationEmailDto);

        EmailDto emailDto = emailService.addReservationEmailDtoToEmail(reservationEmailDto);
        emailService.sendSimpleMessage(emailDto.getTo(), emailDto.getSubject(), emailDto.getContent());

    }

}
