package com.projekat2.UserService.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User {
    private Integer membershipNumber;
    private Integer sessionCount;

    public Client(String email, String firstName, String lastName, String username, String password, Integer membershipNumber, Integer sessionCount) {
        super(email, firstName, lastName, username, password);
        this.membershipNumber = membershipNumber;
        this.sessionCount = sessionCount;
    }

    public Client() {
    }
}
