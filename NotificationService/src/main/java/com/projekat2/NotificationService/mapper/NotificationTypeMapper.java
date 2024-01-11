package com.projekat2.NotificationService.mapper;

import com.projekat2.NotificationService.domain.NotificationType;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeCreateDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {

    public NotificationTypeMapper() {
    }

    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType){
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();

        notificationTypeDto.setTypeName(notificationType.getTypeName());


        return notificationTypeDto;
    }

    public NotificationType notificationTypeCreateDtoToNotificationType(NotificationTypeCreateDto notificationTypeCreateDto){
        NotificationType notificationType = new NotificationType();

        notificationType.setTypeName(notificationType.getTypeName());

        return notificationType;
    }

}
