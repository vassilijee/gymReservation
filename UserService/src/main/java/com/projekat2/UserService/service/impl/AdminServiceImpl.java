package com.projekat2.UserService.service.impl;

import com.projekat2.UserService.domain.Admin;
import com.projekat2.UserService.dto.admin.AdminCreateDto;
import com.projekat2.UserService.dto.admin.AdminDto;
import com.projekat2.UserService.mapper.AdminMapper;
import com.projekat2.UserService.repository.AdminRepository;
import com.projekat2.UserService.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public Page<AdminDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public AdminDto findById(Long id) {
        return null;
    }

    @Override
    public AdminDto add(AdminCreateDto adminCreateDto) {
        Admin admin = adminMapper.adminCreateDtoToAdmin(adminCreateDto);
        adminRepository.save(admin);
        return adminMapper.adminToAdminDto(admin);
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}
