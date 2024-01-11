package com.projekat2.NotificationService.service;



import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeCreateDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;

import java.util.List;

public interface NotificationTypeService {

    NotificationTypeDto findByID(Long id);
    List<NotificationTypeDto> findAll();
    NotificationTypeDto add(NotificationTypeCreateDto notificationTypeCreateDto);
    Boolean delete(Long id);
    NotificationTypeDto update(NotificationTypeDto notificationTypeDto);

}
