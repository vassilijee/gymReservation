package com.projekat2.SessionService.service;

import com.projekat2.SessionService.dto.session.SessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SessionService {
    Page<SessionDto> findAll(Pageable pageable);
}
