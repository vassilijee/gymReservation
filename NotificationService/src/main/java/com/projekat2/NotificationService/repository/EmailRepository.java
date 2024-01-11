package com.projekat2.NotificationService.repository;


import com.projekat2.NotificationService.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> findEmailById(Long id);

}
