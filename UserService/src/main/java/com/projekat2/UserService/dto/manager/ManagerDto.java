package com.projekat2.UserService.dto.manager;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ManagerDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String gymName;
    private LocalDate startDate;
}
