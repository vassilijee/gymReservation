package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.Client;
import com.projekat2.UserService.dto.client.ClientBlockDto;
import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import com.projekat2.UserService.dto.client.ClientUpdateDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDto clientToClientDto(Client client) {
        return new ClientDto(client.getEmail(),client.getFirstName(), client.getLastName(),client.getUsername(), client.getMembershipNumber(), client.getSessionCount());
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setMembershipNumber(clientCreateDto.getMembershipNumber());
        client.setSessionCount(0);
        int desiredLength = 15;
        client.setActivateCode( RandomStringUtils.randomAlphanumeric(desiredLength));
        client.setActivate(false);
        client.setBanned(false);
        return client;
    }


}
