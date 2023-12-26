package com.projekat2.UserService.service.impl;

import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import com.projekat2.UserService.mapper.ClientMapper;
import com.projekat2.UserService.repository.ClientRepository;
import com.projekat2.UserService.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    //    private TokenService tokenService;
    private ClientRepository clientRepository;
    //    private UserStatusRepository userStatusRepository;
    private ClientMapper clientMapper;
//      todo: kad budemo imali token
//    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, UserStatusRepository userStatusRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.tokenService = tokenService;
//        this.clientMapper = userMapper;
//        this.userStatusRepository = userStatusRepository;
//    }


    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::clientToClientDto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::clientToClientDto).get();
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);

    }

}
