package com.projekat2.UserService.dto.manager;

import com.projekat2.UserService.dto.UserCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerUpdateDto extends UserCreateDto {
    private Long id;
}
