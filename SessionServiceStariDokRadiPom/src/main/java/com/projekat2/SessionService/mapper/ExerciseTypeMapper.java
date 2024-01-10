package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.ExerciseType;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeCreateDto;
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

    public ExerciseType exerciseTypeCreateDtoToExerciseType(ExerciseTypeCreateDto exerciseTypeCreateDto) {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setName(exerciseTypeCreateDto.getName());
        exerciseType.setGroupType(exerciseTypeCreateDto.getGroupType());
        exerciseType.setPrice(exerciseTypeCreateDto.getPrice());
        return exerciseType;
    }

}
