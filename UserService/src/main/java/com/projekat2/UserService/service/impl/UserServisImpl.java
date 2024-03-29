package com.projekat2.UserService.service.impl;

import com.projekat2.UserService.domain.Client;
import com.projekat2.UserService.domain.Manager;
import com.projekat2.UserService.domain.User;
import com.projekat2.UserService.dto.client.*;
import com.projekat2.UserService.dto.token.TokenRequestDto;
import com.projekat2.UserService.dto.token.TokenResponseDto;
import com.projekat2.UserService.dto.UserDto;
import com.projekat2.UserService.dto.manager.ManagerBlockDto;
import com.projekat2.UserService.dto.manager.ManagerCreateDto;
import com.projekat2.UserService.dto.manager.ManagerDto;
import com.projekat2.UserService.dto.manager.ManagerUpdateDto;
import com.projekat2.UserService.exception.NotFoundException;
import com.projekat2.UserService.listener.helper.MessageHelper;
import com.projekat2.UserService.mapper.ClientMapper;
import com.projekat2.UserService.mapper.ManagerMapper;
import com.projekat2.UserService.mapper.UserMapper;
import com.projekat2.UserService.repository.UserRepository;
import com.projekat2.UserService.secutiry.service.TokenService;
import com.projekat2.UserService.service.UserServis;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServisImpl implements UserServis {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private ClientMapper clientMapper;
    private ManagerMapper managerMapper;
    private TokenService tokenService;
    private JmsTemplate jmsTemplate;
    private String sendLink;
    private MessageHelper messageHelper;


    public UserServisImpl(UserRepository userRepository, UserMapper userMapper, ClientMapper clientMapper,
                          ManagerMapper managerMapper, TokenService tokenService, JmsTemplate jmsTemplate,
                          @Value("${destination.send.mail}") String sendLink, MessageHelper messageHelper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.clientMapper = clientMapper;
        this.managerMapper = managerMapper;
        this.tokenService = tokenService;
        this.jmsTemplate=jmsTemplate;
        this.sendLink = sendLink;
        this.messageHelper= messageHelper;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public ClientDto findClientById(Long id) {
        return userRepository.findClientById(id).map(clientMapper::clientToClientDto).orElseThrow(()-> new NotFoundException("User ciji je id:" + id + "nije pronadjen"));
    }

    @Override
    public ManagerDto findManagerById(Long id) {
        return userRepository.findManagerById(id).map(managerMapper::managerToManagerDto).orElseThrow(()-> new NotFoundException("User ciji je id:" + id + "nije pronadjen"));
    }

    @Override
    public SessionCountDto getCount(Long id) {
        Client client = userRepository.findClientById(id).get();
        return new SessionCountDto(client.getSessionCount());
    }



    @Override
    public ClientDto registerClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        userRepository.save(client);
        List<String> params = new ArrayList<>();
        params.add(client.getFirstName());
        params.add(client.getLastName());
        params.add("http://localhost:8081/api/client/activate/"+client.getActivateCode());
        EmailDto emailDto = new EmailDto(client.getEmail(), params, "Activation");
        System.out.println(emailDto);
        jmsTemplate.convertAndSend(sendLink,messageHelper.createTextMessage(emailDto));
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ClientDto updateClient(ClientUpdateDto clientUpdateDto) {
        Client client = (Client) userRepository.findById(clientUpdateDto.getId()).orElseThrow(()->new NotFoundException("User ciji je id:" + clientUpdateDto.getId() + "nije pronadjen"));
        if(!clientUpdateDto.getFirstName().matches(""))
            client.setFirstName(clientUpdateDto.getFirstName());
        if(!clientUpdateDto.getLastName().matches(""))
            client.setLastName(clientUpdateDto.getLastName());
        if(!clientUpdateDto.getEmail().matches(""))
            client.setEmail(clientUpdateDto.getEmail());
        if(!clientUpdateDto.getUsername().matches(""))
            client.setUsername(clientUpdateDto.getUsername());
        if(!clientUpdateDto.getPassword().matches("")){
            client.setPassword(clientUpdateDto.getPassword());
            List<String> params = new ArrayList<>();
            params.add(client.getFirstName());
            params.add(client.getLastName());
            EmailDto emailDto = new EmailDto(client.getEmail(), params, "PasswordChange");
            jmsTemplate.convertAndSend(sendLink,messageHelper.createTextMessage(emailDto));
        }
        userRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ClientDto blockClient(ClientBlockDto clientBlockDto) {
        Client client =(Client) userRepository.findById(clientBlockDto.getId()).orElseThrow(()->new NotFoundException("User ciji je id:" + clientBlockDto.getId() + "nije pronadjen"));

        client.setBanned(clientBlockDto.isBlocked());

        userRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        userRepository.save(manager);
//        SendLinkDto sendLinkDto = new SendLinkDto(manager.getActivateCode(),manager.getId(), manager.getFirstName(), manager.getLastName(), manager.getEmail());
//        jmsTemplate.convertAndSend(sendLink, messageHelper.createTextMessage(sendLinkDto));
        return managerMapper.managerToManagerDto(manager);    }

    @Override
    public ManagerDto updateManager(ManagerUpdateDto managerUpdateDto) {
        Manager manager = (Manager) userRepository.findById(managerUpdateDto.getId()).orElseThrow(()->new NotFoundException("User ciji je id:" + managerUpdateDto.getId() + "nije pronadjen"));

        if(!managerUpdateDto.getFirstName().matches(""))
            manager.setFirstName(managerUpdateDto.getFirstName());
        if(!managerUpdateDto.getLastName().matches(""))
            manager.setLastName(managerUpdateDto.getLastName());
        if(!managerUpdateDto.getEmail().matches(""))
            manager.setEmail(managerUpdateDto.getEmail());
        if(!managerUpdateDto.getUsername().matches(""))
            manager.setUsername(managerUpdateDto.getUsername());
        if(!managerUpdateDto.getPassword().matches(""))
            manager.setPassword(managerUpdateDto.getPassword());

        userRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto blockManager(ManagerBlockDto managerBlockDto) {
        Manager manager = (Manager) userRepository.findById(managerBlockDto.getId()).orElseThrow(()->new NotFoundException("User ciji je id:" + managerBlockDto.getId() + "nije pronadjen"));

        manager.setBanned(managerBlockDto.isBlocked());

        userRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public void incrementClintSessionCount(ChangeSessionCountDto changeSessionCountDto) {
        Client client = userRepository.findClientById(changeSessionCountDto.getClientId()).get();
        client.setSessionCount(client.getSessionCount()+1);
        userRepository.save(client);
    }

    @Override
    public void activateUser(String activateCode) {
        Client client = userRepository.findClientByActivateCode(activateCode).get();
            client.setActivate(true);
            userRepository.save(client);
    }

    @Override
    public void decrementClintSessionCount(ChangeSessionCountDto changeSessionCountDto) {
        Client client = userRepository.findClientById(changeSessionCountDto.getClientId()).get();
        client.setSessionCount(client.getSessionCount()-1);
        userRepository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }



    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));



        if(user.getDecriminatorValue().equals("Manager")){
            Manager manager = (Manager) user;
            if(manager.isBanned())
                return  new TokenResponseDto("Nemas dozvolu da pristupis app");
        }
        if(user.getDecriminatorValue().equals("Client")){
            Client client = (Client) user;
            if(client.isBanned())
                return  new TokenResponseDto("Nemas dozvolu da pristupis app");
        }


        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getDecriminatorValue());
        claims.put("email",user.getEmail());
        //claims.put("banned",user.isBanned());

        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
}