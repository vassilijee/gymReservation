package com.projekat2.UserService.controller;

import com.projekat2.UserService.dto.client.ClientBlockDto;
import com.projekat2.UserService.dto.client.ClientCreateDto;
import com.projekat2.UserService.dto.client.ClientDto;
import com.projekat2.UserService.dto.client.ClientUpdateDto;
import com.projekat2.UserService.service.UserServis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    private UserServis userServis;

    public ClientController(UserServis userServis) {
        this.userServis = userServis;
    }

//    @GetMapping
//    public ResponseEntity<Page<GymDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                    Pageable pageable) {
//
//        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userServis.findClientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> registerClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userServis.registerClient(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> updateClient(@RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(userServis.updateClient(clientUpdateDto), HttpStatus.CREATED);
    }

    @PutMapping("/block")
    public ResponseEntity<ClientDto> blockClient(@RequestBody @Valid ClientBlockDto clientBlockDto) {
        return new ResponseEntity<>(userServis.blockClient(clientBlockDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userServis.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
