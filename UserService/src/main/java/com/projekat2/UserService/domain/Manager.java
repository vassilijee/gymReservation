package com.projekat2.UserService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("Manager")
public class Manager extends User {
    private String gymName;
    private LocalDate startDate;
    private String activateCode;
    private boolean activate;
    private boolean blocked;

    public Manager(String email, String firstName, String lastName, String username, String password, String gymName, LocalDate startDate) {
        super(email, firstName, lastName, username, password);
        this.gymName = gymName;
        this.startDate = startDate;

        int desiredLength = 15;
        this.activateCode = RandomStringUtils.randomAlphanumeric(desiredLength);
        this.activate = true;
        this.blocked = false;
    }

    public Manager() {
    }
}
