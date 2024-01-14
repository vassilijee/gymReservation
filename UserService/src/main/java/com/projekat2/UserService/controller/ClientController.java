package com.projekat2.UserService.controller;

import com.projekat2.UserService.dto.client.*;
import com.projekat2.UserService.secutiry.CheckSecurity;
import com.projekat2.UserService.service.UserServis;
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
    @CheckSecurity(roles = {"Admin", "Manager"})
    public ResponseEntity<ClientDto> findClientById(@RequestHeader("authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(userServis.findClientById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/count")
    public ResponseEntity<SessionCountDto> getCount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userServis.getCount(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"Client"})
    public ResponseEntity<ClientDto> registerClient(@RequestHeader("authorization") String authorization, @RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userServis.registerClient(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @CheckSecurity(roles = {"Client"})
    public ResponseEntity<ClientDto> updateClient(@RequestHeader("authorization") String authorization, @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(userServis.updateClient(clientUpdateDto), HttpStatus.CREATED);
    }


    @PutMapping("/block")
    @CheckSecurity(roles = {"Admin", "Manager"})
    public ResponseEntity<ClientDto> blockClient(@RequestHeader("authorization") String authorization, @RequestBody @Valid ClientBlockDto clientBlockDto) {
        return new ResponseEntity<>(userServis.blockClient(clientBlockDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"Admin", "Manager"})
    public ResponseEntity<?> delete(@RequestHeader("authorization") String authorization, @PathVariable("id") Long id) {
        userServis.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
