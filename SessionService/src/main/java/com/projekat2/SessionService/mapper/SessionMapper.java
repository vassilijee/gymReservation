package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.repository.ExerciseTypeRepository;
import com.projekat2.SessionService.repository.GymRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

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

        return new SessionDto(gymMapper.gymToGymDto(session.getGym()),exerciseTypeMapper.exerciseTypeToExerciseTypeDto(session.getExerciseType()), session.getCurrentCount(),session.getDate(), session.getTime());
    }

    public Session sessionCreateDtoToSession(SessionCreateDto sessionCreateDto) {
        Session session = new Session();
        session.setCurrentCount(0);
        session.setGym(gymRepository.findById(sessionCreateDto.getGymId()).get());
        session.setExerciseType(exerciseTypeRepository.findById(sessionCreateDto.getExerciseTypeId()).get());
        LocalDate localDate = session.getDate();
        session.setDayOfTheWeek(localDate.getDayOfWeek().getValue());
        return session;
    }
}
