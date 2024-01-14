package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.client.userServis.dto.ChangeSessionCountDto;
import com.projekat2.SessionService.client.userServis.dto.SessionCountDto;
import com.projekat2.SessionService.domain.Reservation;
import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import com.projekat2.SessionService.helper.MessageHelper;
import com.projekat2.SessionService.mapper.ReservationMapper;
import com.projekat2.SessionService.repository.ReservationRepository;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.service.ReservationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class ReservationServiseImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private SessionRepository sessionRepository;
    private RestTemplate userServiceRestTemplate;
    private JmsTemplate jmsTemplate;
    private String increment;
    private String decrement;
    private MessageHelper messageHelper;

    public ReservationServiseImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, SessionRepository sessionRepository,
                                  RestTemplate userServiceRestTemplate, JmsTemplate jmsTemplate, @Value("${destination.increment.session.count}") String increment, @Value("${destination.decrement.session.count}") String decrement,MessageHelper messageHelper ) {
        this.sessionRepository=sessionRepository;
       this.reservationRepository= reservationRepository;
       this.reservationMapper = reservationMapper;
       this.userServiceRestTemplate = userServiceRestTemplate;
       this.jmsTemplate=jmsTemplate;
       this.increment=increment;
       this.decrement = decrement;
       this.messageHelper = messageHelper;
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
        //Slanje maila ovde
        Session session = sessionRepository.findById(reservation.getSession().getId()).get();
        session.currentCountPlus();
        if(session.getCurrentCount() == session.getExerciseType().getMaxCount())
            session.setFree(false);
        sessionRepository.save(session);
        ResponseEntity<SessionCountDto> sessionCountDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
                reservation.getClientId() + "/count", HttpMethod.GET, null, SessionCountDto.class);
        if(sessionCountDtoResponseEntity.getBody().getSessionCount()!=0 && sessionCountDtoResponseEntity.getBody().getSessionCount()%session.getExerciseType().getDiscount()==0)
            reservation.setPrice(0);
        else
            reservation.setPrice(session.getExerciseType().getPrice());
        reservationRepository.save(reservation);
//        userServiceRestTemplate.exchange("/client/" +
//                reservation.getClientId() + "/increment", HttpMethod.PUT, null, HttpMethod.class);
        ChangeSessionCountDto changeSessionCountDto = new ChangeSessionCountDto(reservation.getClientId());
        jmsTemplate.convertAndSend(increment, messageHelper.createTextMessage(changeSessionCountDto));
        System.out.println(changeSessionCountDto.getClientId());
        return reservationMapper.reservationToReservationDto(reservation);
    }

    public void deleteReservationBySessionId(Long id){
        List<Reservation> reservations = reservationRepository.findAllReservationBySessionId(id);
        for (Reservation reservation : reservations) {
            jmsTemplate.convertAndSend(decrement, messageHelper.createTextMessage(new ChangeSessionCountDto(reservation.getClientId())));
            deleteById(reservation.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
