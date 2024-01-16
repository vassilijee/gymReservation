package com.projekat2.NotificationService.service;



import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeCreateDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationTypeService {

    Page<NotificationTypeDto> findAll(Pageable pageable);
    NotificationTypeDto add(NotificationTypeCreateDto notificationTypeCreateDto);
    Boolean delete(Long id);
    NotificationTypeDto update(NotificationTypeUpdateDto notificationTypeUpdateDto);

}
