package com.projekat2.UserService.dto.manager;

import com.projekat2.UserService.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ManagerDto extends UserDto {

    private String gymName;
    private LocalDate startDate;

    public ManagerDto(String email, String firstName, String lastName, String username, String gymName, LocalDate startDate) {
        super(email, firstName, lastName, username);
        this.gymName = gymName;
        this.startDate = startDate;
    }
}
