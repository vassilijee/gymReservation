package com.projekat2.NotificationService.repository;


import com.projekat2.NotificationService.domain.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    Optional<Mail> findMailById(Long id);

    Page<Mail> findByEmail(String email, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.createdAt between ?1 and ?2")
    Page<Mail> findByDateRange(LocalDate beginDate, LocalDate endDate, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.type.typeName = ?1")
    Page<Mail> findByType(String type, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.type.typeName = ?1 and m.email = ?2")
    Page<Mail> findByTypeAndByEmail(String type, String email,Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.createdAt between ?1 and ?2 and m.email = ?3")
    Page<Mail> findByDateRangeAndByEmail(LocalDate beginDate, LocalDate endDate, String email, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.createdAt between ?1 and ?2 and m.type.typeName = ?3")
    Page<Mail> findByTypeAndByDateRange(LocalDate beginDate, LocalDate endDate, String type, Pageable pageable);

    @Query("SELECT m FROM Mail m WHERE m.type.typeName = ?1 and m.email = ?2 and m.createdAt between ?3 and ?4")
    Page<Mail> findByTypeAndByEmailAndByDateRange(String type, String email, LocalDate beginDate, LocalDate endDate, Pageable pageable);

}
