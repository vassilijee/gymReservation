package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.Client;
import com.projekat2.UserService.domain.Manager;
import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import com.projekat2.UserService.dto.manager.ManagerCreateDto;
import com.projekat2.UserService.dto.manager.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setGymName(manager.getGymName());
        managerDto.setStartDate(manager.getStartDate());
        return managerDto;
    }

    public Manager managerCreateDtotoManager(ManagerCreateDto managerCreateDto) {
        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setGymName(managerCreateDto.getGymName());
        manager.setStartDate(managerCreateDto.getStartDate());
        return manager;
    }
}
