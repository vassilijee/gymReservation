package com.projekat2.NotificationService.dto.emailDto;

import com.projekat2.NotificationService.dto.notificationTypeDto.NotificationTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailCreateDto {
    @NotBlank
    private NotificationTypeDto notificationTypeDto;
    @NotBlank
    private List<String> parameters;
    @NotBlank
    private String email;
}
