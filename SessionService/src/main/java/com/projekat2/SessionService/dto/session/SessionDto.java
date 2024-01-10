package com.projekat2.SessionService.dto.session;


import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SessionDto {

    private GymDto gymDto;
    private ExerciseTypeDto exerciseTypeDto;
    private Integer currentCount;
    private Date date;
    private Time time;
}
