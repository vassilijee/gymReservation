package com.projekat2.SessionService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

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

    private Date date;
    private Time time;
    private boolean free;
    private int dayOfTheWeek;

    public Session(Gym gym, ExerciseType exerciseType, Integer currentCount, Date date, Time time) {
        this.gym = gym;
        this.exerciseType = exerciseType;
        this.currentCount = currentCount;
        this.date = date;
        this.time = time;
        this.free = true;
    }

    public Session() {
    }
}
