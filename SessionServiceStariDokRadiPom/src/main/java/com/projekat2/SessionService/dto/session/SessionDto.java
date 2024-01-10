package com.projekat2.SessionService.dto.session;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class SessionDto {
    private Long id;
    @JsonProperty("gym")
    private GymDto gymDto;
    @JsonProperty("exercisetype")
    private ExerciseTypeDto exerciseTypeDto;
    @Min(value = 0, message = "The value must be positive")
    private Integer currentCount;
}
