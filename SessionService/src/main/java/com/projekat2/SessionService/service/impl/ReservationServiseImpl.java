package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.domain.Reservation;
import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import com.projekat2.SessionService.mapper.GymMapper;
import com.projekat2.SessionService.mapper.ReservationMapper;
import com.projekat2.SessionService.repository.GymRepository;
import com.projekat2.SessionService.repository.ReservationRepository;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationServiseImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private SessionRepository sessionRepository;

    public ReservationServiseImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, SessionRepository sessionRepository) {
        this.sessionRepository=sessionRepository;
       this.reservationRepository= reservationRepository;
       this.reservationMapper = reservationMapper;
    }
    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(reservationMapper::reservationToReservationDto);
    }

    @Override
    public ReservationDto findReservationById(Long id) {
        return reservationMapper.reservationToReservationDto(reservationRepository.findReservationById(id).get());
    }

    @Override
    public ReservationDto add(ReservationCreateDto reservationCreateDto) {
        Reservation reservation = reservationMapper.reservationCreateDtoToReservation(reservationCreateDto);
        Session session = sessionRepository.findById(reservation.getSession().getId()).get();
        session.currentCountPlus();
        sessionRepository.save(session);
        return reservationMapper.reservationToReservationDto(reservationRepository.save(reservation));
    }

    public void deleteReservationBySessionId(Long id){
        List<Reservation> reservations = reservationRepository.findAllReservationBySessionId(id);
        for (Reservation reservation : reservations) {
            deleteById(reservation.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
