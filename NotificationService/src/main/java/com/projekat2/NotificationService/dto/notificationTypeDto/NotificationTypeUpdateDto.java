package com.projekat2.NotificationService.dto.notificationTypeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTypeUpdateDto {
    private Long id;
    private String text;
    private String typeName;
}
