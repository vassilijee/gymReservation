package com.projekat2.SessionService.mapper;

import com.projekat2.SessionService.domain.Reservation;
import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import com.projekat2.SessionService.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationMapper {
    private SessionMapper sessionMapper;
    private SessionRepository sessionRepository;
    public ReservationDto reservationToReservationDto (Reservation reservation){
        return new ReservationDto(sessionMapper.sessionToSessionDto(reservation.getSession()), reservation.getClientId(), reservation.getPrice());
    }
    public Reservation reservationCreateDtoToReservation (ReservationCreateDto reservationCreateDto){
        Reservation reservation = new Reservation();
        reservation.setSession(sessionRepository.findById(reservationCreateDto.getSessionId()).get());
        reservation.setClientId(reservationCreateDto.getClientId());
        return reservation;
    }
}
