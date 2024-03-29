package com.projekat2.NotificationService.dto.notificationTypeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTypeCreateDto {
    @NotNull
    private String typeName;
    @NotNull
    private String text;
}

