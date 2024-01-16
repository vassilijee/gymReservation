package com.projekat2.NotificationService.mapper;

import com.projekat2.NotificationService.domain.Mail;
import com.projekat2.NotificationService.dto.emailDto.*;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {

    public MailDto mailToMailDto(Mail mail)
    {
        MailDto mailDto = new MailDto();
        mailDto.setId(mail.getId());
        mailDto.setNotificationTypeDto(new NotificationTypeDto(mail.getType().getId(), mail.getType().getText(), mail.getType().getTypeName()));
        mailDto.setParameters(mail.getParameters());
        mailDto.setEmail(mail.getEmail());
        return mailDto;
    }
}
