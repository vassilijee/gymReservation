package com.projekat2.SessionService.service;

import com.projekat2.SessionService.dto.gym.GymCreateDto;
import com.projekat2.SessionService.dto.gym.GymDto;
import com.projekat2.SessionService.dto.reservation.ClientCancelReservationDto;
import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    Page<ReservationDto> findAll(Pageable pageable);

    ReservationDto findReservationById(Long id);

    ReservationDto add(ReservationCreateDto reservationCreateDto);

    void deleteReservationBySessionId(Long id);
    void clientCancelReservation(ClientCancelReservationDto clientCancelReservationDto);
    void deleteById(Long id);
}
