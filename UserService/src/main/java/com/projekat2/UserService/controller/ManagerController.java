package com.projekat2.UserService.controller;

import com.projekat2.UserService.dto.manager.ManagerBlockDto;
import com.projekat2.UserService.dto.manager.ManagerCreateDto;
import com.projekat2.UserService.dto.manager.ManagerDto;
import com.projekat2.UserService.dto.manager.ManagerUpdateDto;
import com.projekat2.UserService.secutiry.CheckSecurity;
import com.projekat2.UserService.service.UserServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private UserServis userServis;

    public ManagerController(UserServis userServis) {
        this.userServis = userServis;
    }

//    @GetMapping
//    public ResponseEntity<Page<GymDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                    Pageable pageable) {
//
//        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<ManagerDto> findManagerById(@RequestHeader("authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(userServis.findManagerById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<ManagerDto> registerManager(@RequestHeader("authorization") String authorization, @RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userServis.registerManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<ManagerDto> updateManager(@RequestHeader("authorization") String authorization, @RequestBody @Valid ManagerUpdateDto managerUpdateDto) {
        return new ResponseEntity<>(userServis.updateManager(managerUpdateDto), HttpStatus.CREATED);
    }

    @PutMapping("/block")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<ManagerDto> blockManager(@RequestHeader("authorization") String authorization, @RequestBody @Valid ManagerBlockDto managerBlockDto) {
        return new ResponseEntity<>(userServis.blockManager(managerBlockDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"Admin"})
    public ResponseEntity<?> delete(@RequestHeader("authorization") String authorization, @PathVariable("id") Long id) {
        userServis.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
