package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.dto.session.SessionCancelDto;
import com.projekat2.SessionService.dto.session.SessionCreateDto;
import com.projekat2.SessionService.dto.session.SessionDto;
import com.projekat2.SessionService.secutiry.CheckSecurity;
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
    @GetMapping("/allfree")
    public ResponseEntity<Page<SessionDto>> getAllFreeSessions(Pageable pageable) {
        return new ResponseEntity<>(sessionService.findAllFreeSessions(pageable), HttpStatus.OK);
    }
    //dal za svaki da se napravi poseban dto
//    @GetMapping("/{groupType}")
//    public ResponseEntity<Page<SessionDto>> getGroupTypeSessions(Pageable pageable) {
//        return new ResponseEntity<>(sessionService.findGroupTypeSession(pageable), HttpStatus.OK);
//    }
//    @GetMapping
//    public ResponseEntity<Page<SessionDto>> getAllSessions(Pageable pageable) {
//        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
//    }
//    @GetMapping
//    public ResponseEntity<Page<SessionDto>> getAllSessions(Pageable pageable) {
//        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
//    }
//    @GetMapping
//    public ResponseEntity<Page<SessionDto>> getAllSessions(Pageable pageable) {
//        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
//    }
//    @GetMapping
//    public ResponseEntity<Page<SessionDto>> getAllSessions(Pageable pageable) {
//        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
//    }
//    @GetMapping
//    public ResponseEntity<Page<SessionDto>> getAllSessions(Pageable pageable) {
//        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
//    }
    @GetMapping("/sort")
    public ResponseEntity<Page<SessionDto>> getSortSessions(Pageable pageable) {
        return new ResponseEntity<>(sessionService.findsortByTimeSessions(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(sessionService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"Manager"})
    public ResponseEntity<SessionDto> add(@RequestHeader("authorization") String authorization, @RequestBody @Valid SessionCreateDto sessionCreateDto) {
        return new ResponseEntity<>(sessionService.add(sessionCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    @CheckSecurity(roles = {"Manager"})
    public ResponseEntity<?> cancel(@RequestHeader("authorization") String authorization, @RequestBody @Valid SessionCancelDto sessionCancelDto) {
        sessionService.cancelSession(sessionCancelDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sessionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
