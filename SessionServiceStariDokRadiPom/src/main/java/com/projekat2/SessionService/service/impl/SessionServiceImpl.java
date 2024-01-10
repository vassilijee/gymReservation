package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.mapper.SessionMapper;
import com.projekat2.SessionService.repository.SessionRepository;
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

    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public Page<SessionDto> findAll(Pageable pageable) {
        return sessionRepository.findAll(pageable).map(sessionMapper::sessionToSessionDto);
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
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }
}
