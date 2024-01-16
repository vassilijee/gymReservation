package com.projekat2.UserService.service;

import com.projekat2.UserService.dto.client.*;
import com.projekat2.UserService.dto.token.TokenRequestDto;
import com.projekat2.UserService.dto.token.TokenResponseDto;
import com.projekat2.UserService.dto.UserDto;
import com.projekat2.UserService.dto.manager.ManagerBlockDto;
import com.projekat2.UserService.dto.manager.ManagerCreateDto;
import com.projekat2.UserService.dto.manager.ManagerDto;
import com.projekat2.UserService.dto.manager.ManagerUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserServis {
    Page<UserDto> findAll(Pageable pageable);

    //napraviti pojedinacne metode za svaki tip usera po potrebi njihovoj i koristi njjihov dto
    ClientDto findClientById(Long id);
    ManagerDto findManagerById(Long id);

    SessionCountDto getCount(Long id);
    ClientDto registerClient(ClientCreateDto clientCreateDto);
    ClientDto updateClient(ClientUpdateDto clientUpdateDto);
    ClientDto blockClient(ClientBlockDto clientBlockDto);
    ManagerDto registerManager(ManagerCreateDto managerCreateDto);
    ManagerDto updateManager(ManagerUpdateDto managerUpdateDto);
    ManagerDto blockManager(ManagerBlockDto managerBlockDto);
    void incrementClintSessionCount(ChangeSessionCountDto changeSessionCountDto);
    void activateUser(String activatecode);
    void decrementClintSessionCount(ChangeSessionCountDto changeSessionCountDto);
    void deleteById(Long id);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

}
