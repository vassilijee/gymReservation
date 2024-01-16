package com.projekat2.SessionService.repository;

import com.projekat2.SessionService.domain.Session;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SessionRepository  extends JpaRepository<Session, Long> {
    @Query("SELECT s FROM Session s WHERE s.free=true")
    Page<Session> findAllFreeSessions (Pageable pageable);

    @Query("SELECT s FROM Session s WHERE s.date = ?1")
    List<Session> findSessionOfTheDay(LocalDate date);

    @Query("SELECT s FROM Session s WHERE s.free=true AND s.exerciseType.groupType = ?1")
    Page<Session> findGroupTypeSession (String groupType, Pageable pageable);

    @Query("SELECT s FROM Session s WHERE s.free=true AND s.exerciseType.name = ?1")
    Page<Session> findExerciesTypeSessions (String exerciseType, Pageable pageable);

    @Query("SELECT s FROM Session s WHERE s.free=true AND s.dayOfTheWeek = ?1")
    Page<Session> findDayOfTheWeekSessions (int dayOfTheWeek, Pageable pageable);

    @Query("SELECT s FROM Session s WHERE s.free=true AND s.exerciseType.groupType = ?1 AND s.exerciseType.name = ?2")
    Page<Session> findGroupTypeANDExerciseTypeSessions (String groupType, String exerciseType, Pageable pageable);

    @Query("SELECT s FROM Session s WHERE s.free=true AND s.exerciseType.groupType = ?1 AND s.exerciseType.name = ?2 AND  s.dayOfTheWeek = ?3")
    Page<Session> findGroupTypeANDExerciseTypeANDDaySessions (String groupType, String exerciseType, int day, Pageable pageable);

    @Query("SELECT s FROM Session s WHERE s.free=true AND s.exerciseType.groupType = ?1 AND  s.dayOfTheWeek = ?2")
    Page<Session> findGroupTypeANDDaySessions (String groupType, int day, Pageable pageable);

    //dal treba upit za sortiranje ili to da se sortira u aplikaciji
    @Query("SELECT s FROM Session s ORDER BY s.time ASC")
    Page<Session> findsortByTimeSessions (Pageable pageable);


}
