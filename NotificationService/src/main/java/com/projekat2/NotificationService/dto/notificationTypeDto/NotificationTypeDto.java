package com.projekat2.NotificationService.dto.notificationTypeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTypeDto {
    private Long id;
    private String typeName;
    private String text;
}
