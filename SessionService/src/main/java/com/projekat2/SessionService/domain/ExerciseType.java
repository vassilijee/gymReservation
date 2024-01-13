package com.projekat2.SessionService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "exercisetype")
public class ExerciseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String groupType;
    private Integer price;
    private int discount;

    private int maxCount;

    public ExerciseType(String name, String groupType, Integer price, int discount, int maxCount) {
        this.name = name;
        this.groupType = groupType;
        this.price = price;
        this.discount = discount;
        this.maxCount = maxCount;
    }

    public ExerciseType() {
    }
}
