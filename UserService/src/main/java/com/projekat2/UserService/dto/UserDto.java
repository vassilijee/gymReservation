package com.projekat2.UserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    // napraviti ban dto za clienta i managera samo da updateuje ban polje da ne bi popunjavao sve podatke pri banovanju
    // banovati po usernameu/email
    private String email;
    private String firstName;
    private String lastName;
    private String username;

    public UserDto(String email, String firstName, String lastName, String username) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
