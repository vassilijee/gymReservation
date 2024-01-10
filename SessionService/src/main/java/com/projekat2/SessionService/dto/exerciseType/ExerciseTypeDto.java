package com.projekat2.SessionService.dto.exerciseType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ExerciseTypeDto {
    private String name;
    private String groupType;
    private Integer price;
}
