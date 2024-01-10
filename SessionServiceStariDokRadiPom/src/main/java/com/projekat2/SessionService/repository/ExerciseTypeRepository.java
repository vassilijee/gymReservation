package com.projekat2.SessionService.repository;

import com.projekat2.SessionService.domain.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {
}
