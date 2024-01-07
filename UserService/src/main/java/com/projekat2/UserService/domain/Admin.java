package com.projekat2.UserService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {
    public Admin(String email, String firstName, String lastName, String username, String password) {
        super(email, firstName, lastName, username, password);
    }

    public Admin() {
    }
}
