package com.projekat2.NotificationService.listeners;


import com.projekat2.NotificationService.dto.emailDto.EmailDto;
import com.projekat2.NotificationService.listeners.helper.MessageHelper;
import com.projekat2.NotificationService.service.EmailService;
import org.springframework.jms.annotation.JmsListener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.stereotype.Component;

@Component
public class SendEmailListener {
    private final MessageHelper messageHelper;
    private final EmailService emailService;


    public SendEmailListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;

    }

    @JmsListener(destination = "${destination.send.mail}", concurrency = "5-10")
    public void sendEmail(Message message) throws JMSException {
        System.out.println("uslo");
        EmailDto emailDto = messageHelper.getMessage(message, EmailDto.class);
        System.out.println(emailDto);
        emailService.sendSimpleMessage(emailDto.getEmail(), emailDto.getType(), emailDto.getParams());
    }

}
