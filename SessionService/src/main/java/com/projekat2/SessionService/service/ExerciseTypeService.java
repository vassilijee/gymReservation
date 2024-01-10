package com.projekat2.SessionService.service;

import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeCreateDto;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseTypeService {
    Page<ExerciseTypeDto> findAll(Pageable pageable);
    ExerciseTypeDto findById(Long id);

    ExerciseTypeDto add(ExerciseTypeCreateDto exerciseTypeCreateDto);

    //GymDto update(Long id, G productUpdateDto);

    void deleteById(Long id);
}
