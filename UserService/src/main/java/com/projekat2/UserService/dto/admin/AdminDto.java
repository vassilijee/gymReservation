package com.projekat2.UserService.dto.admin;

import com.projekat2.UserService.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto extends UserDto {


    public AdminDto(String email, String firstName, String lastName, String username) {
        super(email, firstName, lastName, username);
    }
}
