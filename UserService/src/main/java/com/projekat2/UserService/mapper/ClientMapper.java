package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.Client;
import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setMembershipNumber(client.getMembershipNumber());
        clientDto.setSessionCount(client.getSessionCount());
        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setMembershipNumber(clientCreateDto.getMembershipNumber());
        client.setSessionCount(clientCreateDto.getSessionCount());
        return client;
    }
}
