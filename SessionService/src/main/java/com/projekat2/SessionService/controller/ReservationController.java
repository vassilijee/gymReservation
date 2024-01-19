package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.reservation.ClientCancelReservationDto;
import com.projekat2.SessionService.dto.reservation.ReservationCreateClientDto;
import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.secutiry.CheckSecurity;
import com.projekat2.SessionService.secutiry.service.TokenService;
import com.projekat2.SessionService.service.ReservationService;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;
    private TokenService tokenService;
    private SessionRepository sessionRepository;

    public ReservationController(ReservationService reservationService, TokenService tokenService, SessionRepository sessionRepository) {
        this.reservationService = reservationService;
        this.tokenService = tokenService;
        this.sessionRepository = sessionRepository;

    }

    @GetMapping
    public ResponseEntity<Page<ReservationDto>> getAllReservations(Pageable pageable) {
        return new ResponseEntity<>(reservationService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reservationService.findReservationById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"Client", "Manager", "Admin"})
    public ResponseEntity<ReservationDto> add(@RequestHeader("authorization") String authorization, @RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        return new ResponseEntity<>(reservationService.add(reservationCreateDto), HttpStatus.CREATED);
    }

//    @PostMapping("/client")
//    @CheckSecurity(roles = {"Client"})
//    public ResponseEntity<ReservationDto> addC(@RequestHeader("authorization") String authorization, @RequestBody @Valid ReservationCreateClientDto reservationCreateClientDto) {
//        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
//        Session session = sessionRepository.findSessionTimeandDate(reservationCreateClientDto.getExerciseTypeName(), LocalDate.parse(reservationCreateClientDto.getDate()), LocalTime.reservationCreateClientDto.getTime());
//        ReservationCreateDto reservationCreateDto = new ReservationCreateDto(claims.get("id").toString(),);
//        return new ResponseEntity<>(reservationService.add(reservationCreateDto), HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"Client"})
    public ResponseEntity<?> delete(@RequestHeader("authorization") String authorization, @RequestBody @Valid ClientCancelReservationDto cancelReservationDto) {
        reservationService.clientCancelReservation(cancelReservationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
