package com.projekat2.UserService.service;

import com.projekat2.UserService.dto.admin.AdminCreateDto;
import com.projekat2.UserService.dto.admin.AdminDto;
import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientDto> findAll(Pageable pageable);

    ClientDto findById(Long id);

    ClientDto add(ClientCreateDto clientCreateDto);

    //GymDto update(Long id, G productUpdateDto);

    void deleteById(Long id);
}
