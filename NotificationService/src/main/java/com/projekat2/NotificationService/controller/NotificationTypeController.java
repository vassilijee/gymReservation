package com.projekat2.NotificationService.controller;

import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeCreateDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeUpdateDto;
import com.projekat2.NotificationService.repository.NotificationTypeRepository;
import com.projekat2.NotificationService.secutiry.CheckSecurity;
import com.projekat2.NotificationService.service.NotificationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@RequestMapping("/notificationtype")
public class NotificationTypeController {
    private NotificationTypeService notificationTypeService;
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeController(NotificationTypeService notificationTypeService, NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeService = notificationTypeService;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @GetMapping
   // @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<Page<NotificationTypeDto>> getAllNotificationTypes(@RequestHeader("Authorization") String authorization,
                                                                             Pageable pageable)
    {
        return new ResponseEntity<>(notificationTypeService.findAll(pageable), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    //@CheckSecurity(roles = {"Admin"})
    public void deleteNotificationType(@RequestHeader("Authorization") String authorization,
                                       @PathVariable Long id)
    {
        notificationTypeService.delete(id);
    }

    @PutMapping
  //  @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<NotificationTypeDto> updateNotificationType(@RequestHeader("Authorization") String authorization,
                                                                      @RequestBody @Valid NotificationTypeUpdateDto notificationTypeUpdateDto)
    {
        if(notificationTypeRepository.findById(notificationTypeUpdateDto.getId()).isPresent())
            return new ResponseEntity<>(notificationTypeService.update(notificationTypeUpdateDto),HttpStatus.OK);
        return null;
    }
    @PostMapping
  //  @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<NotificationTypeDto> addNotificationType(@RequestHeader("Authorization") String authorization,
                                                                   @RequestBody @Valid NotificationTypeCreateDto notificationTypeCreateDto)
    {
        return new ResponseEntity<>(notificationTypeService.add(notificationTypeCreateDto),HttpStatus.OK);
    }
}
