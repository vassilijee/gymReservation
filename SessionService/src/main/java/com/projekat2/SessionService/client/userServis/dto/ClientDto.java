package com.projekat2.SessionService.client.userServis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private Integer membershipNumber;
    private Integer sessionCount;
}
