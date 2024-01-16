package com.projekat2.SessionService.scheduler;

import com.projekat2.SessionService.domain.Reservation;
import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.repository.ReservationRepository;
import com.projekat2.SessionService.repository.SessionRepository;
import com.projekat2.SessionService.service.ReservationService;
import com.projekat2.SessionService.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class ReminderScheduler {

    private SessionRepository sessionRepository;
    private ReservationService reservationService;

    @Scheduled(cron="10 0 9 * * *")
    public void reminder(){
        List<Session> allsession = sessionRepository.findSessionOfTheDay(LocalDate.now().plusDays(1));
        for (Session session:
             allsession) {
            reservationService.remindeClientBySessionId(session.getId());

        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void cancel(){
        List<Session> allsession = sessionRepository.findSessionOfTheDay(LocalDate.now().plusDays(1));
        for (Session session:
                allsession) {
            if(session.getCurrentCount()<=3)
                reservationService.deleteReservationBySessionId(session.getId());

        }

    }

}
