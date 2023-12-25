package com.projekat2.SessionService.dto.exerciseType;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ExerciseTypeCreateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String groupType;
    @Min(value = 0, message = "The value must be positive")
    private Integer price;
}
