package com.projekat2.NotificationService.dto.emailDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailDto {
    private Long id;
    private String from;
    private String to;
    private String subject;
    private String content;
    private String date;
}
