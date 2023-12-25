package com.projekat2.SessionService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Gym gym;
    @ManyToOne(optional = false)
    private ExerciseType exerciseType;
    private Integer currentCount;

    public Session(Gym gym, ExerciseType exerciseType, Integer currentCount) {
        this.gym = gym;
        this.exerciseType = exerciseType;
        this.currentCount = currentCount;
    }

    public Session() {
    }
}
