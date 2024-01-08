package com.projekat2.UserService.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("Client")
public class Client extends User {
    private Integer membershipNumber;
    private Integer sessionCount;
    private String activateCode;
    private boolean activate;
    private boolean banned;

    public Client(String email, String firstName, String lastName, String username, String password, Integer membershipNumber, Integer sessionCount) {
        super(email, firstName, lastName, username, password);
        this.membershipNumber = membershipNumber;
        this.sessionCount = sessionCount;

        int desiredLength = 15;
        this.activateCode = RandomStringUtils.randomAlphanumeric(desiredLength);
        this.activate = true;
        this.banned = false;

    }

    public Client() {
    }
}
