package com.projekat2.SessionService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalDate date;
    private LocalTime time;
    private boolean free;
    private int dayOfTheWeek;

    public Session(Gym gym, ExerciseType exerciseType, Integer currentCount, LocalDate date, LocalTime time) {
        this.gym = gym;
        this.exerciseType = exerciseType;
        this.currentCount = currentCount;
        this.date = date;
        this.time = time;
        this.free = true;
        this.dayOfTheWeek=date.getDayOfWeek().getValue();
    }
    public Session(Gym gym, ExerciseType exerciseType, Integer currentCount, LocalDate date, LocalTime time, boolean bo) {
        this.gym = gym;
        this.exerciseType = exerciseType;
        this.currentCount = currentCount;
        this.date = date;
        this.time = time;
        this.free = bo;
        this.dayOfTheWeek=date.getDayOfWeek().getValue();
    }
    public Session() {
    }

    public void currentCountPlus(){
        currentCount=currentCount+1;
    }

    public void currentCountMinus(){
        currentCount = currentCount-1;
    }
}
