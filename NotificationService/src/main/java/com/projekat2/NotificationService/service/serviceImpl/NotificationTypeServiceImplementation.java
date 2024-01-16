package com.projekat2.NotificationService.service.serviceImpl;


import com.projekat2.NotificationService.domain.NotificationType;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeCreateDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeUpdateDto;
import com.projekat2.NotificationService.exception.NotFoundException;
import com.projekat2.NotificationService.mapper.NotificationTypeMapper;
import com.projekat2.NotificationService.repository.NotificationTypeRepository;
import com.projekat2.NotificationService.service.NotificationTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationTypeServiceImplementation implements NotificationTypeService {

    NotificationTypeMapper notificationTypeMapper;
    NotificationTypeRepository notificationTypeRepository;


    public NotificationTypeServiceImplementation(NotificationTypeMapper notificationTypeMapper, NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeMapper = notificationTypeMapper;
        this.notificationTypeRepository = notificationTypeRepository;
    }
    @Override
    public Page<NotificationTypeDto> findAll(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable).map(notificationTypeMapper::notificationTypeToNotificationTypeDto);
    }

    @Override
    public NotificationTypeDto add(NotificationTypeCreateDto notificationTypeCreateDto) {

        NotificationType notificationType = notificationTypeMapper.notificationTypeCreateDtoToNotificationType(notificationTypeCreateDto);

        notificationTypeRepository.save(notificationType);

        return notificationTypeMapper.notificationTypeToNotificationTypeDto(notificationType);
    }

    @Override
    public Boolean delete(Long id) {
        NotificationType notificationType = notificationTypeRepository.findById(id).orElseThrow(()-> new NotFoundException("User ciji je id:" + id + "nije pronadjen"));

        notificationTypeRepository.delete(notificationType);
        return true;
    }

    @Override
    public NotificationTypeDto update(NotificationTypeUpdateDto notificationTypeUpdateDto) {

        NotificationType notificationType = notificationTypeRepository.findById(notificationTypeUpdateDto.getId()).orElseThrow(()-> new NotFoundException("User ciji je id:" + notificationTypeUpdateDto.getId() + "nije pronadjen"));
        if(!notificationTypeUpdateDto.getTypeName().equals(""))
            notificationType.setTypeName(notificationTypeUpdateDto.getTypeName());
        if(!notificationTypeUpdateDto.getText().equals(""))
            notificationType.setText(notificationTypeUpdateDto.getText());
        notificationTypeRepository.save(notificationType);
        return notificationTypeMapper.notificationTypeToNotificationTypeDto(notificationType);
    }

}
