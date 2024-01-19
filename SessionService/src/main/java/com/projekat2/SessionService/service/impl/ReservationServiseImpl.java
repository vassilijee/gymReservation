package com.projekat2.SessionService.service.impl;

import com.projekat2.SessionService.client.notifivationServis.dto.EmailDto;
import com.projekat2.SessionService.client.userServis.dto.ChangeSessionCountDto;
import com.projekat2.SessionService.client.userServis.dto.ClientDto;
import com.projekat2.SessionService.client.userServis.dto.ManagerDto;
import com.projekat2.SessionService.domain.Reservation;
import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.dto.reservation.ClientCancelReservationDto;
import com.projekat2.SessionService.dto.reservation.ReservationCreateDto;
import com.projekat2.SessionService.dto.reservation.ReservationDto;
import com.projekat2.SessionService.exception.NotFoundException;
import com.projekat2.SessionService.helper.MessageHelper;
import com.projekat2.SessionService.mapper.ReservationMapper;
import com.projekat2.SessionService.repository.ReservationRepository;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.service.ReservationService;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    private String sendEmail;
    private MessageHelper messageHelper;
    private Retry userServiceRetry;

    public ReservationServiseImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, SessionRepository sessionRepository,
                                  RestTemplate userServiceRestTemplate, JmsTemplate jmsTemplate, @Value("${destination.increment.session.count}") String increment, @Value("${destination.decrement.session.count}") String decrement,
                                  @Value("${destination.send.mail}") String sendEmail, Retry userServiceRetry,MessageHelper messageHelper ) {
        this.sessionRepository=sessionRepository;
       this.reservationRepository= reservationRepository;
       this.reservationMapper = reservationMapper;
       this.userServiceRestTemplate = userServiceRestTemplate;
       this.jmsTemplate=jmsTemplate;
       this.increment=increment;
       this.decrement = decrement;
       this.sendEmail = sendEmail;
       this.messageHelper = messageHelper;
       this.userServiceRetry = userServiceRetry;
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
        if(!session.isFree())
            return new ReservationDto(null,null, null);
        session.currentCountPlus();
        if(session.getCurrentCount() == session.getExerciseType().getMaxCount())
            session.setFree(false);
//
//        ResponseEntity<SessionCountDto> sessionCountDtoResponseEntity = userServiceRestTemplate.exchange("/client/" +
//                reservation.getClientId() + "/count", HttpMethod.GET, null, SessionCountDto.class);
        ClientDto clientDto = Retry.decorateSupplier(userServiceRetry, ()->getClient(reservation.getClientId())).get();
        if(clientDto.getSessionCount()!=0 && clientDto.getSessionCount()%session.getExerciseType().getDiscount()==0)
            reservation.setPrice(0);
        else
            reservation.setPrice(session.getExerciseType().getPrice());
        reservationRepository.save(reservation);
        sessionRepository.save(session);

        //Slanje maila korisniku
        EmailDto emailDto = new EmailDto();
        emailDto.setType("Reservation");
        List<String> params = new ArrayList<>();
        params.add(clientDto.getFirstName());
        params.add(clientDto.getLastName());
        params.add(session.getDate().toString());
        params.add(session.getTime().toString());
        emailDto.setEmail(clientDto.getEmail());
        emailDto.setParams(params);
        System.out.println(emailDto);
        jmsTemplate.convertAndSend(sendEmail, messageHelper.createTextMessage(emailDto));
        //Slanje maila menadzeru
        ManagerDto managerDto = Retry.decorateSupplier(userServiceRetry, ()->getManager(2L)).get();
        EmailDto emailDto2 = new EmailDto();
        emailDto2.setType("Reservation");
        List<String> params2 = new ArrayList<>();
        params2.add(managerDto.getFirstName());
        params2.add(managerDto.getLastName());
        params2.add(session.getDate().toString());
        params2.add(session.getTime().toString());
        emailDto2.setEmail(managerDto.getEmail());
        emailDto2.setParams(params2);
        System.out.println(emailDto);
        jmsTemplate.convertAndSend(sendEmail,messageHelper.createTextMessage(emailDto2));

        ChangeSessionCountDto changeSessionCountDto = new ChangeSessionCountDto(reservation.getClientId());
        jmsTemplate.convertAndSend(increment, messageHelper.createTextMessage(changeSessionCountDto));
        System.out.println(changeSessionCountDto.getClientId());
        return reservationMapper.reservationToReservationDto(reservation);
    }

    private ClientDto getClient(Long clientId) {
        //get projection from movie service
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting client for id: " + clientId);
        try {
            Thread.sleep(5000);
            return userServiceRestTemplate.exchange("/client/" +
                    clientId , HttpMethod.GET, null, ClientDto.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with id: %d not found.", clientId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Internal server error.");
        }
    }

    private ManagerDto getManager(Long managerId) {
        //get projection from movie service
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting manager for id: " + managerId);
        try {
            Thread.sleep(5000);
            return userServiceRestTemplate.exchange("/manager/" +
                    managerId , HttpMethod.GET, null, ManagerDto.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Manager with id: %d not found.", managerId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Internal server error.");
        }
    }

    public void deleteReservationBySessionId(Long id){
        List<Reservation> reservations = reservationRepository.findAllReservationBySessionId(id);
        Session session = sessionRepository.findById(id).get();
        for (Reservation reservation : reservations) {
            ClientDto clientDto = Retry.decorateSupplier(userServiceRetry, ()->getClient(reservation.getClientId())).get();
            EmailDto emailDto = new EmailDto();
            emailDto.setType("Cancellation");
            List<String> params = new ArrayList<>();
            params.add(clientDto.getFirstName());
            params.add(clientDto.getLastName());
            params.add(session.getDate().toString());
            params.add(session.getTime().toString());
            emailDto.setEmail(clientDto.getEmail());
            emailDto.setParams(params);
            jmsTemplate.convertAndSend(sendEmail, messageHelper.createTextMessage(emailDto));

            deleteById(reservation.getId());
            jmsTemplate.convertAndSend(decrement, messageHelper.createTextMessage(new ChangeSessionCountDto(reservation.getClientId())));
        }
    }

    @Override
    public void clientCancelReservation(ClientCancelReservationDto clientCancelReservationDto) {
        Reservation reservation = reservationRepository.findReservationByClientIdANDSessionId(clientCancelReservationDto.getClientId(), clientCancelReservationDto.getSessionId()).get();
        Session session = sessionRepository.findById(clientCancelReservationDto.getSessionId()).get();

        //Slanje maila
        ClientDto clientDto = Retry.decorateSupplier(userServiceRetry, ()->getClient(reservation.getClientId())).get();
        EmailDto emailDto = new EmailDto();
        emailDto.setType("Cancellation");
        List<String> params = new ArrayList<>();
        params.add(clientDto.getFirstName());
        params.add(clientDto.getLastName());
        params.add(session.getDate().toString());
        params.add(session.getTime().toString());
        emailDto.setEmail(clientDto.getEmail());
        emailDto.setParams(params);
        jmsTemplate.convertAndSend(sendEmail, messageHelper.createTextMessage(emailDto));
        //Slanje maila menadzeru
        ManagerDto managerDto = Retry.decorateSupplier(userServiceRetry, ()->getManager(2L)).get();
        EmailDto emailDto2 = new EmailDto();
        emailDto2.setType("Cancellation");
        List<String> params2 = new ArrayList<>();
        params2.add(managerDto.getFirstName());
        params2.add(managerDto.getLastName());
        params2.add(session.getDate().toString());
        params2.add(session.getTime().toString());
        emailDto2.setEmail(managerDto.getEmail());
        emailDto2.setParams(params2);
        jmsTemplate.convertAndSend(sendEmail,messageHelper.createTextMessage(emailDto2));

        ChangeSessionCountDto changeSessionCountDto = new ChangeSessionCountDto(reservation.getClientId());
        jmsTemplate.convertAndSend(decrement,messageHelper.createTextMessage(changeSessionCountDto));
    }

    @Override
    public void remindeClientBySessionId(Long id) {
        List<Reservation> reservations = reservationRepository.findAllReservationBySessionId(id);
        Session session = sessionRepository.findById(id).get();
        for (Reservation reservation : reservations) {
            ClientDto clientDto = Retry.decorateSupplier(userServiceRetry, ()->getClient(reservation.getClientId())).get();
            EmailDto emailDto = new EmailDto();
            emailDto.setType("Reminder");
            List<String> params = new ArrayList<>();
            params.add(clientDto.getFirstName());
            params.add(clientDto.getLastName());
            params.add(session.getTime().toString());
            emailDto.setEmail(clientDto.getEmail());
            emailDto.setParams(params);
            jmsTemplate.convertAndSend(sendEmail, messageHelper.createTextMessage(emailDto));

        }
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
