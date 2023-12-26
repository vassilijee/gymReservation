package com.projekat2.UserService.service;

import com.projekat2.UserService.dto.manager.ManagerCreateDto;
import com.projekat2.UserService.dto.manager.ManagerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);

    ManagerDto findById(Long id);

    ManagerDto add(ManagerCreateDto managerCreateDto);

    //GymDto update(Long id, G productUpdateDto);

    void deleteById(Long id);}
