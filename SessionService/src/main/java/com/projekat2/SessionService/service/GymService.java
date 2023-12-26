package com.projekat2.SessionService.service;

import com.projekat2.SessionService.dto.gym.GymCreateDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GymService {
    Page<GymDto> findAll(Pageable pageable);

    GymDto findById(Long id);

    GymDto add(GymCreateDto gymCreateDto);

    //GymDto update(Long id, G productUpdateDto);

    void deleteById(Long id);
}
