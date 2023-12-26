package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.Admin;
import com.projekat2.UserService.domain.Admin;
import com.projekat2.UserService.dto.admin.AdminCreateDto;
import com.projekat2.UserService.dto.admin.AdminDto;
import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setEmail(admin.getEmail());
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setUsername(admin.getUsername());
        return adminDto;
    }

    public Admin adminCreateDtoToAdmin(AdminCreateDto adminCreateDto) {
        Admin admin = new Admin();
        admin.setEmail(adminCreateDto.getEmail());
        admin.setFirstName(adminCreateDto.getFirstName());
        admin.setLastName(adminCreateDto.getLastName());
        admin.setUsername(adminCreateDto.getUsername());
        admin.setPassword(adminCreateDto.getPassword());
        return admin;
    }
}
