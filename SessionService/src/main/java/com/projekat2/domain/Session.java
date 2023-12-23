package com.projekat2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "session")
public class Session {
    @Id
    private Long id;

    @ManyToOne
    private Gym gymId;

    @ManyToOne
    private ExerciseType exerciseTypeId;

    private Integer currentCount;

}
