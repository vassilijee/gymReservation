package com.projekat2.UserService.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TokenResponseDto {
    private String token;

    public TokenResponseDto(String token) {
        this.token = token;
    }

    public TokenResponseDto() {
    }
}
