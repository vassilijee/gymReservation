package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.mapper.SessionMapper;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private SessionMapper sessionMapper;

    @Override
    public Page<SessionDto> findAll(Pageable pageable) {
        return sessionRepository.findAll(pageable).map(sessionMapper::sessionToSessionDto);
    }
}
