package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.Manager;
import com.projekat2.UserService.dto.manager.ManagerBlockDto;
import com.projekat2.UserService.dto.manager.ManagerCreateDto;
import com.projekat2.UserService.dto.manager.ManagerDto;
import com.projekat2.UserService.dto.manager.ManagerUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    public ManagerDto managerToManagerDto(Manager manager) {
        return new ManagerDto(manager.getEmail(), manager.getFirstName(), manager.getLastName(), manager.getUsername(), manager.getGymName(), manager.getStartDate());
    }


    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) {
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

    public Manager managerUpdateDtoToManager(ManagerUpdateDto managerUpdateDto){

        return null;
    }

    public Manager managerBlockDtoToManager(ManagerBlockDto managerBlockDto){

        return  null;
    }
}
