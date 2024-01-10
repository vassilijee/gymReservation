package com.projekat2.SessionService.runner;

import com.projekat2.SessionService.domain.ExerciseType;
import com.projekat2.SessionService.domain.Gym;
import com.projekat2.SessionService.domain.Session;
import com.projekat2.SessionService.repository.ExerciseTypeRepository;
import com.projekat2.SessionService.repository.GymRepository;
import com.projekat2.SessionService.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

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
        //insert gym
        Gym gym = gymRepository.save(new Gym("Gym", "Gym with space for all types of exercise.", 5));
        //insert exerciseType
        ExerciseType personal = exerciseTypeRepository.save(new ExerciseType("personal training", "individual", 1500));
        ExerciseType yoga = exerciseTypeRepository.save(new ExerciseType("yoga", "group", 3000));
        ExerciseType pilates = exerciseTypeRepository.save(new ExerciseType("pilates", "group", 2000));
        //insert sessions
        sessionRepository.save(new Session(gym, personal, 0, new Date(2024,1,13), new Time(10,0,0)));
        sessionRepository.save(new Session(gym, yoga, 0, new Date(2024,1,13), new Time(11,0,0)));
        sessionRepository.save(new Session(gym, pilates, 0, new Date(2024,1,13), new Time(12,0,0)));
    }
}
