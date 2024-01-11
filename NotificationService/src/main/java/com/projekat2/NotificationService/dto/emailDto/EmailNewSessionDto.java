package com.projekat2.NotificationService.dto.emailDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailNewSessionDto {
    private String email;
    private String gymName;
    private String user;
    private String manager;
    private String date;
    private String exerciseType;
}
