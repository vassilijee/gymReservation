package com.projekat2.UserService.service;

import com.projekat2.UserService.dto.client.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientDto> findAll(Pageable pageable);

}
