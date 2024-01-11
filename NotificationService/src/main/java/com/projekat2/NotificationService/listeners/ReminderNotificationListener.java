package com.projekat2.NotificationService.listeners;


import com.projekat2.NotificationService.listeners.helper.MessageHelper;
import com.projekat2.NotificationService.mapper.NotificationTypeMapper;
import com.projekat2.NotificationService.service.EmailService;

public class ReminderNotificationListener {
    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationTypeMapper notificationTypeMapper;

    public ReminderNotificationListener(MessageHelper messageHelper, EmailService emailService, NotificationTypeMapper notificationTypeMapper) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationTypeMapper = notificationTypeMapper;
    }

   //TODO
}
