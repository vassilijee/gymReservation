package com.projekat2.UserService.repository;


import com.projekat2.UserService.domain.Client;
import com.projekat2.UserService.domain.Manager;
import com.projekat2.UserService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<Client> findClientByUsernameAndPassword(String username, String password);

    Optional<Client> findClientById(Long id);
    Optional<Client> findClientByActivateCode(String activateCode);
    Optional<Manager> findManagerById(Long id);




}
