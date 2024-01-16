package com.projekat2.NotificationService.dto.emailDto;

import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private Long id;
    private String email;
    private NotificationTypeDto notificationTypeDto;
    private List<String> parameters;
}
