package com.projekat2.UserService.dto.client;

import com.projekat2.UserService.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends UserDto {
    private Integer membershipNumber;
    private Integer sessionCount;

    public ClientDto(String email, String firstName, String lastName, String username, Integer membershipNumber, Integer sessionCount) {
        super(email, firstName, lastName, username);
        this.membershipNumber = membershipNumber;
        this.sessionCount = sessionCount;
    }
}
