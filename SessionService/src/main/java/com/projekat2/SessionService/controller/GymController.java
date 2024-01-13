package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.dto.gym.GymCreateDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import com.projekat2.SessionService.dto.gym.GymUpdateDto;
import com.projekat2.SessionService.secutiry.CheckSecurity;
import com.projekat2.SessionService.service.GymService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/gym")
public class GymController {
    private GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

//    @GetMapping
//    public ResponseEntity<Page<GymDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                    Pageable pageable) {
//
//        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Page<GymDto>> getAllGyms(Pageable pageable) {
        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(gymService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GymDto> add(@RequestBody @Valid GymCreateDto gymCreateDto) {
        return new ResponseEntity<>(gymService.add(gymCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    @CheckSecurity(roles = {"Manager"})
    public ResponseEntity<GymDto> update(@RequestHeader("authorization") String authorization, @RequestBody @Valid GymUpdateDto gymUpdateDto) {
        return new ResponseEntity<>(gymService.update(gymUpdateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        gymService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
