package com.projekat2.UserService.dto.client;

import com.projekat2.UserService.dto.UserCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientBlockDto extends UserCreateDto {
    private Long id;
    private boolean blocked;
}
