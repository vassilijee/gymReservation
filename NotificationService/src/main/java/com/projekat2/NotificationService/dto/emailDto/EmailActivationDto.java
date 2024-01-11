package com.projekat2.NotificationService.dto.emailDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailActivationDto {
    private String email;
    private String firstName;
    private String lastName;
    private String link;
}
