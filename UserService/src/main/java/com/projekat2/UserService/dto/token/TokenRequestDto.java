package com.projekat2.UserService.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TokenRequestDto {
    private String email;
    private String password;
}
