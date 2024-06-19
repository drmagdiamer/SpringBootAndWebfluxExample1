package com.javainnovations.springBootTutorial.controller;


import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import com.javainnovations.springBootTutorial.model.pojo.Person;
import com.javainnovations.springBootTutorial.service.definition.DbService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private DbService dbService;

    @GetMapping("/")
    public String root(){
        log.info("Hello from root");
        return "HI";
    }

    @GetMapping(value="/savePersonFromGetQueryParam")
    public PersonDto savePersonFromGetQueryParam(@RequestParam  String name,
                                                    @RequestParam int age,
                                                    @RequestParam boolean hasMilitaryService){
        log.info("Hello from savePersonFromForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        PersonDto personDtoResult = dbService.savePerson(new Person(name, age, hasMilitaryService));
        if(personDtoResult.getId()!= 0){
            log.info("Person saved: " + personDtoResult.toString());
        } else {
            log.info("Person error in saving: " + personDtoResult.toString());
        }
        return personDtoResult;
    }

    @GetMapping(value="/savePersonFromGetQueryParamProfessional")
    public ResponseEntity<PersonDto> savePersonFromGetQueryParamProfessional(@RequestParam  String name,
                                              @RequestParam int age,
                                              @RequestParam boolean hasMilitaryService){
        log.info("Hello from savePersonFromGetQueryParamProfessional");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        try {
            PersonDto personDtoResult = dbService.savePerson(new Person(name, age, hasMilitaryService));
            if(personDtoResult.getId()!= 0){
                log.info("Person saved: " + personDtoResult.toString());
            } else {
                log.info("Person error in saving: " + personDtoResult.toString());
            }
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/getPerson/{id}")
                    .buildAndExpand(personDtoResult.getId()).toUri();
            return ResponseEntity.created(location).body(personDtoResult);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/savePersonFromGetForm")
    public Person savePersonFromForm(@RequestParam String name,
                                     @RequestParam int age, @RequestParam boolean hasMilitaryService)
    {
        log.info("Hello from savePersonFromForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);

        PersonDto personDtoResult = dbService.savePerson(new Person(name, age, hasMilitaryService));
        if(personDtoResult.getId()!= 0){
            log.info("Person saved: " + personDtoResult.toString());
        } else {
            log.info("Person error in saving: " + personDtoResult.toString());
        }
        return personDtoResult.toPerson();
    }

    @PostMapping("/savePersonFromPostForm")
    public Person savePersonFromPostForm(@RequestParam String name,
                                         @RequestParam int age, @RequestParam boolean hasMilitaryService)
    {
        log.info("Hello from savePersonFromPostForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);

        PersonDto personDtoResult = dbService.savePerson(new Person(name, age, hasMilitaryService));
        if(personDtoResult.getId()!= 0){
            log.info("Person saved: " + personDtoResult.toString());
        } else {
            log.info("Person error in saving: " + personDtoResult.toString());
        }
        return personDtoResult.toPerson();
    }

    @PostMapping("/savePersonUsingBody")
    public Person savePersonUsingBody(@Validated @RequestBody(required = false) Person person){
        log.info("Hello from savePersonFromPostForm");
        log.info("Name: " + person.getName());
        log.info("Age: " + person.getAge());
        log.info("Has Military Service: " + person.isHasMilitaryService());

        PersonDto personDtoResult = dbService.savePerson(person);
        if(personDtoResult.getId()!= 0){
            log.info("Person saved: " + personDtoResult.toString());
        } else {
            log.info("Person error in saving: " + personDtoResult.toString());
        }
        return personDtoResult.toPerson();
    }

    @GetMapping("/savePersonFromPathParam/{name}/{age}/{hasMilitaryService}")
    public Person savePersonFromPathParam(@PathVariable("name") String name,
                                          @PathVariable("age") int age,
                                          @PathVariable("hasMilitaryService") boolean hasMilitaryService){
        log.info("Hello from savePersonFromPathParam");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        PersonDto personDtoResult = dbService.savePerson(new Person(name, age, hasMilitaryService));
        if(personDtoResult.getId()!= 0){
            log.info("Person saved: " + personDtoResult.toString());
        } else {
            log.info("Person error in saving: " + personDtoResult.toString());
        }
        return personDtoResult.toPerson();
    }

    @GetMapping("/savePersonFromHeaderParam")
    public Person savePersonFromHeaderParam(@RequestHeader(value = "name") String name,
                                            @RequestHeader(value = "age") int age,
                                            @RequestHeader(value = "hasMilitaryService") boolean hasMilitaryService){
        log.info("Hello from savePersonFromHeaderParam");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        PersonDto personDtoResult = dbService.savePerson(new Person(name, age, hasMilitaryService));
        if(personDtoResult.getId()!= 0){
            log.info("Person saved: " + personDtoResult.toString());
        } else {
            log.info("Person error in saving: " + personDtoResult.toString());
        }
        return personDtoResult.toPerson();
    }

    @GetMapping("/getPerson/{id}")
    public PersonDto getPerson(@PathVariable("id") String id){
        log.info("Hello from getPerson");
        log.info("id: " + id);

        return dbService.getPerson(Integer.parseInt(id));
    }

    @GetMapping("/test")
    public String test(){
        log.info("test");
        return (3/0)+ " is bad";
    }

    @GetMapping("/test2")
    public String test2(){
        Person p = null;
        return p.toString();
    }

    @ExceptionHandler({ ArithmeticException .class })
    public ResponseEntity<String> handleArithmeticException (final ArithmeticException  ex) {

        String bodyOfResponse = ex.getMessage();
        log.info("Testing ArithmeticException Handler {}", bodyOfResponse);
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<String> handleException(final NullPointerException ex) {
        String bodyOfResponse = ex.getMessage();
        log.info("Testing Exception Handler {}", bodyOfResponse);
        log.info("Testing Exception Handler {}", ex.toString());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        String bodyOfResponse = ex.getMessage();
        log.info("handleMethodArgumentTypeMismatchException {}", bodyOfResponse);
        log.info("handleMethodArgumentTypeMismatchException {}", ex.toString());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }


}
