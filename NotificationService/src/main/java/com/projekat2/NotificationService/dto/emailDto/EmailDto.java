package com.projekat2.NotificationService.dto.emailDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EmailDto {
    private String email;
    private String type;
    private List<String> params;

    @Override
    public String toString() {
        return "EmailDto{" +
                "email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", params=" + params +
                '}';
    }
}
