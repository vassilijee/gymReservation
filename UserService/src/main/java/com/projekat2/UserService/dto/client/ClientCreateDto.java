package com.projekat2.UserService.dto.client;

import com.projekat2.UserService.dto.UserCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ClientCreateDto {
    private Integer membershipNumber;
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
}
