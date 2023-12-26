package com.projekat2.UserService.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private Integer membershipNumber;
    private Integer sessionCount;
}
