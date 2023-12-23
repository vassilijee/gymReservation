package com.projekat2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//Uređivanje podataka o fiskulturnoj sali - menadžer fiskulturne sale može da
//unosi i ažurira podatke o fiskulturnoj sali u kojoj radi. Podaci koje fiskulturna
//sala može da ima su ime, kratak opis, broj personalnih trenera, tipove
//treninga koje ima na raspolaganju (powerlifting, pilates, kalistenika, joga, itd.),
//i cenu za svaki od tipova treninga. Treninge je potrebno podeliti u dve grupe,
//individualne i grupne, npr. kalistetika i powerlifting bi mogli da budu
//individualni, dok bi joga i pilates mogli da budu grupni. Svi tipovi treninga su ili
//individualni ili grupni.

@Entity
@Table(name = "gym")
public class Gym {
    @Id
    private Long id;

    private String gymName;
    private String gymDesc;
    private Integer personnelCount;


}
