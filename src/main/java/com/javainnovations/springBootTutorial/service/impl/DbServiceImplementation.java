package com.javainnovations.springBootTutorial.service.impl;

import com.javainnovations.springBootTutorial.model.pojo.Person;
import com.javainnovations.springBootTutorial.repository.PersonRepo;
import com.javainnovations.springBootTutorial.service.definition.DbService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javainnovations.springBootTutorial.model.dto.PersonDto;

import java.util.Optional;

@Log4j2
@Service
public class DbServiceImplementation implements DbService {

    @Autowired
    private PersonRepo personRepo;

    //Major  issue: concurrency-control issue
    //private int customerId;

    @Override
    public PersonDto getPerson(int id) {
        Optional<PersonDto> personDtoOptional = personRepo.findById(id);
        personDtoOptional.ifPresent(personDto -> {
                log.info("Person found: " + personDto.toString());
            });
        PersonDto result = null;
        if(personDtoOptional.isPresent()){
            PersonDto personDto =  personDtoOptional.get();
            result = personDto;
        }

        return result;

    }

    @Override
    public PersonDto savePerson(Person person) {
        PersonDto personDto = person.toPersonDto();

        PersonDto personDtoResult = personRepo.save(personDto);

        return  personDtoResult;
    }
}
