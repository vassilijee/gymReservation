package com.projekat2.NotificationService.listeners;

import com.example.SK_Prj2_Rakic_Vasic.NotificationService.listeners.helper.MessageHelper;
import com.example.SK_Prj2_Rakic_Vasic.NotificationService.mapper.NotificationTypeMapper;
import com.example.SK_Prj2_Rakic_Vasic.NotificationService.service.EmailService;

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
