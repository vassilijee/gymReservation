package com.projekat2.UserService.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager extends User {
    private String gymName;
    private LocalDate startDate;

    public Manager(String email, String firstName, String lastName, String username, String password, String gymName, LocalDate startDate) {
        super(email, firstName, lastName, username, password);
        this.gymName = gymName;
        this.startDate = startDate;
    }

    public Manager() {
    }
}
