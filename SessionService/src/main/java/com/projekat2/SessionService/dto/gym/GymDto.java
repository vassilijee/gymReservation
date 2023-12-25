package com.projekat2.SessionService.dto.gym;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class GymDto {
    private Long id;
    @NotEmpty(message = "Name cant be empty")
    private String gymName;
    private String gymDesc;
}
