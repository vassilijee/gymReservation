package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeCreateDto;
import com.projekat2.SessionService.dto.exerciseType.ExerciseTypeDto;
import com.projekat2.SessionService.service.ExerciseTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exercise_type")
public class ExerciseTypeController {
    private ExerciseTypeService exerciseTypeService;

    public ExerciseTypeController(ExerciseTypeService exerciseTypeService) {
        this.exerciseTypeService = exerciseTypeService;
    }

//    @GetMapping
//    public ResponseEntity<Page<GymDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                    Pageable pageable) {
//
//        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Page<ExerciseTypeDto>> getAllExerciseTypes(Pageable pageable) {
        return new ResponseEntity<>(exerciseTypeService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseTypeDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(exerciseTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExerciseTypeDto> add(@RequestBody @Valid ExerciseTypeCreateDto exerciseTypeCreateDto) {
        return new ResponseEntity<>(exerciseTypeService.add(exerciseTypeCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        exerciseTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
