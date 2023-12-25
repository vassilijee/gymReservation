package com.projekat2.SessionService.repository;

import com.projekat2.SessionService.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository  extends JpaRepository<Session, Long> {
}
