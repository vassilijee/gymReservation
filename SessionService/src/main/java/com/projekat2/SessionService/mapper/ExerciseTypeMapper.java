package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.ExerciseType;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeDto;
import org.springframework.stereotype.Component;

@Component
public class ExerciseTypeMapper {
    public ExerciseTypeDto exerciseTypeToExerciseTypeDto(ExerciseType exerciseType) {
        ExerciseTypeDto exerciseTypeDto = new ExerciseTypeDto();
        exerciseTypeDto.setId(exerciseType.getId());
        exerciseTypeDto.setName(exerciseType.getName());
        exerciseTypeDto.setGroupType(exerciseType.getGroupType());
        exerciseTypeDto.setPrice(exerciseType.getPrice());
        return exerciseTypeDto;
    }

    public ExerciseType gymCreateDtoToGym(ExerciseTypeDto exerciseTypeDto) {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setName(exerciseTypeDto.getName());
        exerciseType.setGroupType(exerciseTypeDto.getGroupType());
        exerciseType.setPrice(exerciseTypeDto.getPrice());
        return exerciseType;
    }

}
