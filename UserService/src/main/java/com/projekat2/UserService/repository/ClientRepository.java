package com.projekat2.UserService.repository;

import com.projekat2.UserService.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByUsernameAndPassword(String username, String password);
}
