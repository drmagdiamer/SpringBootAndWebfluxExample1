package com.javainnovations.springBootTutorial.model.dto;

import com.javainnovations.springBootTutorial.model.pojo.Person;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;


@Getter
@Setter
@Log4j2
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "person")
public class PersonDto {
    @Id
    //If using Oracle or Postgres
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)

    //If using MySql or Ms SQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Person toPerson() {
        return Person.builder()
                .name(this.name)
                .age(this.age)
                .hasMilitaryService(this.hasMilitaryService)
                .build();
    }

    @Column(name = "has_military_service")
    private boolean hasMilitaryService;

}