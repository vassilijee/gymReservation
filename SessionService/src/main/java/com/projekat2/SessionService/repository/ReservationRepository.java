package com.projekat2.SessionService.repository;

import com.projekat2.SessionService.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findReservationById(Long id);
    @Query("SELECT r FROM Reservation r WHERE r.session.id=?1")
    List<Reservation> findAllReservationBySessionId(Long id);

    @Query("SELECT r FROM Reservation r WHERE r.clientId=?1 AND r.session.id=?2")
    Optional<Reservation> findReservationByClientIdANDSessionId(Long clientId, Long sessionId);
}
