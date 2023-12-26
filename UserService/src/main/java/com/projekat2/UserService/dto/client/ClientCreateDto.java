package com.projekat2.UserService.dto.client;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClientCreateDto {
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    private Integer membershipNumber;
    @Min(value = 0, message = "The value must be positive")
    private Integer sessionCount;
}
