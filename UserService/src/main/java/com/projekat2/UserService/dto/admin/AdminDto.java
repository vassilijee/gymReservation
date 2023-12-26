package com.projekat2.UserService.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
}
