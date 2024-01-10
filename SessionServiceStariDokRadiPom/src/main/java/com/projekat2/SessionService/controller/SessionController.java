package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionController {
    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

//    @GetMapping
//    public ResponseEntity<Page<GymDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                    Pageable pageable) {
//
//        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Page<SessionDto>> getAllSessions(Pageable pageable) {
        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(sessionService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SessionDto> add(@RequestBody @Valid SessionCreateDto sessionCreateDto) {
        return new ResponseEntity<>(sessionService.add(sessionCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sessionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
