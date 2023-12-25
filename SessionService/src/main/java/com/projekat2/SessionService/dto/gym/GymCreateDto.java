package com.projekat2.SessionService.dto.gym;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GymCreateDto {
    @NotBlank
    private String gymName;
    private String gymDesc;
    @Min(value = 0, message = "The value must be positive")
    private Integer personnelCount;
}
