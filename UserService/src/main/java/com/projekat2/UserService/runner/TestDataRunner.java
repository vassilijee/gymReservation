package com.projekat2.UserService.runner;


import com.projekat2.UserService.domain.Admin;
import com.projekat2.UserService.domain.Client;
import com.projekat2.UserService.domain.Manager;
import com.projekat2.UserService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Transactional
@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {
    private UserRepository userRepository;


    public TestDataRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        //Insert gym
//        Gym gymTest = new Gym();
//        gymTest.setGymName("TERETANa");
//        gymTest.setGymDesc("TERETANA TERETANA TERETANA");
//        gymTest.setPersonnelCount(5);
//        gymRepository.save(gymTest);
//        //Insert exercise type
//        ExerciseType exerciseType = new ExerciseType();
//        exerciseType.setName("Plivanje");
//        exerciseType.setGroupType("Grupno");
//        exerciseType.setPrice(1000);
//        exerciseTypeRepository.save(exerciseType);
//        insert gym
        Client client1 = userRepository.save(new Client("emalil1@email.com", "ime1", "prezime1", "username1", "password111", 1111, 0));
        Client client2 = userRepository.save(new Client("emalil2@email.com", "ime2", "prezime2", "username2", "password222", 2222, 0));
//        insert exerciseType
        LocalDate myDate3 = LocalDate.of(2014, 2, 14);
        Manager manager1 = userRepository.save(new Manager("emalil1@email.com", "ime1", "prezime1", "username1", "password111", "gymName1", myDate3));
        Manager manager2 = userRepository.save(new Manager("emalil2@email.com", "ime2", "prezime2", "username2", "password222", "gymName2", myDate3));
//        insert admin
        Admin admin1 = userRepository.save(new Admin("emalil2@email.com", "ime2", "prezime2", "username2", "password222"));
//        sessionRepository.save(new Session(gym1, exerciseType2, 0));
//        sessionRepository.save(new Session(gym2, exerciseType3, 0));
    }
}
