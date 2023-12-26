package com.projekat2.SessionService.runner;

import com.projekat2.SessionService.domain.ExerciseType;
import com.projekat2.SessionService.domain.Gym;
import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.repository.ExerciseTypeRepository;
import com.projekat2.SessionService.repository.GymRepository;
import com.projekat2.SessionService.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {
    private GymRepository gymRepository;
    private ExerciseTypeRepository exerciseTypeRepository;
    private SessionRepository sessionRepository;

    public TestDataRunner(GymRepository gymRepository, ExerciseTypeRepository exerciseTypeRepository, SessionRepository sessionRepository) {
        this.gymRepository = gymRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.sessionRepository = sessionRepository;
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
        //insert gym
        Gym gym1 = gymRepository.save(new Gym("gym 1", "description gym 1", 5));
        Gym gym2 = gymRepository.save(new Gym("gym 2", "description gym 2", 10));
        //insert exerciseType
        ExerciseType exerciseType1 = exerciseTypeRepository.save(new ExerciseType("exercise 1", "individual", 1500));
        ExerciseType exerciseType2 = exerciseTypeRepository.save(new ExerciseType("exercise 2", "group", 3000));
        ExerciseType exerciseType3 = exerciseTypeRepository.save(new ExerciseType("exercise 3", "group", 2000));
        //insert sessions
        sessionRepository.save(new Session(gym1, exerciseType1, 1));
        sessionRepository.save(new Session(gym1, exerciseType2, 1));
        sessionRepository.save(new Session(gym2, exerciseType3, 1));
    }
}
