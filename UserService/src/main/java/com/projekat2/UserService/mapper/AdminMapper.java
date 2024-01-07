package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.Admin;
import com.projekat2.UserService.dto.admin.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminDto adminToAdminDto(Admin admin) {
        return new AdminDto(admin.getEmail(), admin.getFirstName(), admin.getLastName(), admin.getUsername());
    }

}
