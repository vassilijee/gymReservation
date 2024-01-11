package com.projekat2.NotificationService.listeners;


import com.projekat2.NotificationService.dto.emailDto.EmailActivationDto;
import com.projekat2.NotificationService.dto.emailDto.EmailDto;
import com.projekat2.NotificationService.listeners.helper.MessageHelper;
import com.projekat2.NotificationService.service.EmailService;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;

public class ActivateEmailNotificationListener {
    private final MessageHelper messageHelper;
    private final EmailService emailService;


    public ActivateEmailNotificationListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;

    }

    @JmsListener(destination = "${destination.activationEmail}", concurrency = "5-10")
    public void activationEmailNotification(Message message) throws JMSException {
        EmailActivationDto activationEmailDto = messageHelper.getMessage(message, EmailActivationDto.class);
        System.out.println(activationEmailDto);

        EmailDto emailDto = emailService.addActivationEmailToEmail(activationEmailDto);


        emailService.sendSimpleMessage(emailDto.getTo(), emailDto.getSubject(), emailDto.getContent());
    }

}
