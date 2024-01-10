package com.projekat2.SessionService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "gym")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gymName;
    private String gymDesc;
    private Integer personnelCount;

    public Gym(String gymName, String gymDesc, Integer personnelCount) {
        this.gymName = gymName;
        this.gymDesc = gymDesc;
        this.personnelCount = personnelCount;
    }

    public Gym() {

    }
}
