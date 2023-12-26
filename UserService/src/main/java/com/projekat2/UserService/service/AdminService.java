package com.projekat2.UserService.service;

import com.projekat2.UserService.dto.admin.AdminCreateDto;
import com.projekat2.UserService.dto.admin.AdminDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<AdminDto> findAll(Pageable pageable);

    AdminDto findById(Long id);

    AdminDto add(AdminCreateDto adminCreateDto);

    //GymDto update(Long id, G productUpdateDto);

    void deleteById(Long id);
}
