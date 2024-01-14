package com.projekat2.UserService.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendLinkDto {
    private String activateCode;
    private Long clientId;
    private String firstName;
    private String lastName;
}
