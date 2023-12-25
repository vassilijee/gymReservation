package com.projekat2.SessionService.dto.exerciseType;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ExerciseTypeDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String groupType;
    @Min(value = 0, message = "The value must be positive")
    private Integer price;
}
