package com.javainnovations.springBootTutorial.service.definition;

import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import com.javainnovations.springBootTutorial.model.pojo.Person;

public interface DbService {
    Person  getPerson(int id);
    PersonDto savePerson(Person person);
}
