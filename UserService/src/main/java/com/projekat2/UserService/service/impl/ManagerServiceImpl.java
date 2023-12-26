package com.projekat2.UserService.service.impl;

import com.projekat2.UserService.dto.manager.ManagerDto;
import com.projekat2.UserService.mapper.ManagerMapper;
import com.projekat2.UserService.repository.ManagerRepository;
import com.projekat2.UserService.service.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    //    private TokenService tokenService;
    private ManagerRepository managerRepository;
    //    private UserStatusRepository userStatusRepository;
    private ManagerMapper managerMapper;
//      todo: kad budemo imali token
//    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, UserStatusRepository userStatusRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.tokenService = tokenService;
//        this.clientMapper = userMapper;
//        this.userStatusRepository = userStatusRepository;
//    }


    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(managerMapper::managerToManagerDto);
    }
}
