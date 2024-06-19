package com.javainnovations.springBootTutorial.model.pojo;

import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import com.javainnovations.springBootTutorial.model.validator.ValidPerson;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Log4j2
@Builder
@AllArgsConstructor
@ToString
@ValidPerson
public class Person {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 10, message = "Name must not exceed 10 characters") //max should be = to DB length
    private String name;
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;
    private boolean hasMilitaryService;
    private boolean female;//false == m, true == f
    private List<Car> car;

    public Person() {
        this.car = new ArrayList<>();
    }

    public Person(String name, int age, boolean hasMilitaryService) {
        this();
        this.name = name;
        this.age = age;
        this.hasMilitaryService = hasMilitaryService;
    }

    public PersonDto toPersonDto() {
        return PersonDto.builder()
                .name(this.name)
                .age(this.age)
                .hasMilitaryService(this.hasMilitaryService)
                .build();
    }


}


