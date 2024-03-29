package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.domain.ExerciseType;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeCreateDto;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeDto;
import com.projekat2.SessionService.mapper.ExerciseTypeMapper;
import com.projekat2.SessionService.repository.ExerciseTypeRepository;
import com.projekat2.SessionService.service.ExerciseTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExerciseTypeServiceImpl implements ExerciseTypeService {
    private ExerciseTypeRepository exerciseTypeRepository;
    private ExerciseTypeMapper exerciseTypeMapper;

    public ExerciseTypeServiceImpl(ExerciseTypeRepository exerciseTypeRepository, ExerciseTypeMapper exerciseTypeMapper) {
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.exerciseTypeMapper = exerciseTypeMapper;
    }

    @Override
    public Page<ExerciseTypeDto> findAll(Pageable pageable) {
        return exerciseTypeRepository.findAll(pageable)
                .map(exerciseTypeMapper::exerciseTypeToExerciseTypeDto);
    }

    @Override
    public ExerciseTypeDto findById(Long id) {
        return exerciseTypeRepository.findById(id).map(exerciseTypeMapper::exerciseTypeToExerciseTypeDto).get();    }

    @Override
    public ExerciseTypeDto add(ExerciseTypeCreateDto exerciseTypeCreateDto) {
        ExerciseType exerciseType = exerciseTypeMapper.exerciseTypeCreateDtoToExerciseType(exerciseTypeCreateDto);
        exerciseTypeRepository.save(exerciseType);
        return exerciseTypeMapper.exerciseTypeToExerciseTypeDto(exerciseType);    }

    @Override
    public void deleteById(Long id) {
        exerciseTypeRepository.deleteById(id);
    }
}
