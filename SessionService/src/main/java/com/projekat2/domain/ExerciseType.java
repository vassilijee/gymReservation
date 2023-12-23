package com.projekat2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "exerciseType")
public class ExerciseType {
    @Id
    private Long id;
    private String name;
    private String type;
    private Integer price;

}
