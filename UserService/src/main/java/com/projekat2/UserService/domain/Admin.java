package com.projekat2.UserService.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Admin extends User {
    public Admin(String email, String firstName, String lastName, String username, String password) {
        super(email, firstName, lastName, username, password);
    }
}
