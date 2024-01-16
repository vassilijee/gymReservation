package com.projekat2.UserService.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String email;
    private List<String> params;
    private String type;

    @Override
    public String toString() {
        return "EmailDto{" +
                "email='" + email + '\'' +
                ", params=" + params +
                ", type='" + type + '\'' +
                '}';
    }
}
