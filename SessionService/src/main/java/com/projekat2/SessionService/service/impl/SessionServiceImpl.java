package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.session.SessionCancelDto;
import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.mapper.SessionMapper;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.service.ReservationService;
import com.projekat2.SessionService.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private SessionMapper sessionMapper;

    private ReservationService reservationServis;

    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper, ReservationService reservationService) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.reservationServis = reservationService;
    }

    @Override
    public Page<SessionDto> findAll(Pageable pageable) {
        return sessionRepository.findAll(pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findAllFreeSessions(Pageable pageable) {
        return sessionRepository.findAllFreeSessions(pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findGroupTypeSession(String groupType, Pageable pageable) {
        return sessionRepository.findGroupTypeSession(groupType, pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findExerciesTypeSessions(String exerciseType, Pageable pageable) {
        return sessionRepository.findExerciesTypeSessions(exerciseType, pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findDayOfTheWeekSessions(int dayOfTheWeek, Pageable pageable) {
        return sessionRepository.findDayOfTheWeekSessions(dayOfTheWeek, pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findGroupTypeANDExerciseTypeSessions(String groupType, String exerciseType, Pageable pageable) {
        return sessionRepository.findGroupTypeANDExerciseTypeSessions(groupType, exerciseType, pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findGroupTypeANDExerciseTypeANDDaySessions(String groupType, String exerciseType, int day, Pageable pageable) {
        return sessionRepository.findGroupTypeANDExerciseTypeANDDaySessions(groupType, exerciseType, day, pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findGroupTypeANDDaySessions(String groupType, int day, Pageable pageable) {
        return sessionRepository.findGroupTypeANDDaySessions(groupType, day, pageable).map(sessionMapper::sessionToSessionDto);
    }

    @Override
    public Page<SessionDto> findsortByTimeSessions(Pageable pageable) {
        return sessionRepository.findsortByTimeSessions(pageable).map(sessionMapper::sessionToSessionDto);
    }


    @Override
    public SessionDto findById(Long id) {
        return sessionRepository.findById(id).map(sessionMapper::sessionToSessionDto).get();    }

    @Override
    public SessionDto add(SessionCreateDto sessionCreateDto) {
        Session session = sessionMapper.sessionCreateDtoToSession(sessionCreateDto);
        sessionRepository.save(session);
        return sessionMapper.sessionToSessionDto(session);    }

    @Override
    public void cancelSession(SessionCancelDto sessionCancelDto) {
        Session session = sessionRepository.findById(sessionCancelDto.getId()).get();
        reservationServis.deleteReservationBySessionId(session.getId());
        session.setFree(false);
        session.setCurrentCount(0);
    }

    @Override
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }
}
