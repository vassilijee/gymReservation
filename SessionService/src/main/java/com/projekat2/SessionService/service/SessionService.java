package com.projekat2.SessionService.service;

import com.projekat2.SessionService.dto.session.SessionCancelDto;
import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SessionService {
    Page<SessionDto> findAll(Pageable pageable);
    Page<SessionDto> findAllFreeSessions(Pageable pageable);
    Page<SessionDto> findGroupTypeSession (String groupType, Pageable pageable);
    Page<SessionDto> findExerciesTypeSessions (String exerciseType, Pageable pageable);
    Page<SessionDto> findDayOfTheWeekSessions (int dayOfTheWeek, Pageable pageable);
    Page<SessionDto> findGroupTypeANDExerciseTypeSessions (String groupType, String exerciseType, Pageable pageable);
    Page<SessionDto> findGroupTypeANDExerciseTypeANDDaySessions (String groupType, String exerciseType, int day, Pageable pageable);
    Page<SessionDto> findGroupTypeANDDaySessions (String groupType, int day, Pageable pageable);

    Page<SessionDto> findsortByTimeSessions (Pageable pageable);

    SessionDto findById(Long id);

    SessionDto add(SessionCreateDto sessionCreateDto);

    void cancelSession(SessionCancelDto sessionCancelDto);

    void deleteById(Long id);
}
