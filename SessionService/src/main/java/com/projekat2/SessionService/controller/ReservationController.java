package com.projekat2.SessionService.controller;

import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import com.projekat2.SessionService.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
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
    public ResponseEntity<ReservationDto> add(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        return new ResponseEntity<>(reservationService.add(reservationCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
