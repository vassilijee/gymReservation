package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.repository.ExerciseTypeRepository;
import com.projekat2.SessionService.repository.GymRepository;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {
    private GymRepository gymRepository;
    private ExerciseTypeRepository exerciseTypeRepository;
    private GymMapper gymMapper;
    private ExerciseTypeMapper exerciseTypeMapper;

    public SessionMapper(GymRepository gymRepository, ExerciseTypeRepository exerciseTypeRepository, GymMapper gymMapper, ExerciseTypeMapper exerciseTypeMapper) {
        this.gymRepository = gymRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.gymMapper = gymMapper;
        this.exerciseTypeMapper = exerciseTypeMapper;
    }

    public SessionDto sessionToSessionDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setGymDto(gymMapper.gymToGymDto(session.getGym()));
        sessionDto.setExerciseTypeDto(exerciseTypeMapper.exerciseTypeToExerciseTypeDto(session.getExerciseType()));
        return sessionDto;
    }

    public Session sessionCreateDtoToSession(SessionCreateDto sessionCreateDto) {
        Session session = new Session();
        session.setCurrentCount(sessionCreateDto.getCurrentCount());
        session.setGym(gymRepository.findById(sessionCreateDto.getGymId()).get());
        session.setExerciseType(exerciseTypeRepository.findById(sessionCreateDto.getExerciseTypeId()).get());
        return session;
    }
}
