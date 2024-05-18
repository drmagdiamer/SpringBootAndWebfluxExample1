package com.javainnovations.springBootTutorial.controller;


import com.javainnovations.springBootTutorial.model.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class MainController {
    @GetMapping("/")
    public String root(){
        log.info("Hello from root");
        return "HI";
    }

    @PostMapping("/")
    public String root2(){
        log.info("Hello from root2");
        return "Hello From Post";
    }

    @GetMapping("/savePersonFromGetForm")
    public String savePersonFromForm(String name, int age, boolean hasMilitaryService){
        log.info("Hello from savePersonFromForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);

        return "Hello from savePersonFromWebForm";
    }

    @PostMapping("/savePersonFromPostForm")
    public String savePersonFromPostForm(String name, int age, boolean hasMilitaryService){
        log.info("Hello from savePersonFromPostForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);

        return "Hello from savePersonFromWebForm";
    }

    @PostMapping("/savePersonUsingBody")
    public String savePersonUsingBody(@RequestBody(required = false) Person person){
        log.info("Hello from savePersonFromPostForm");
        log.info("Name: " + person.getName());
        log.info("Age: " + person.getAge());
        log.info("Has Military Service: " + person.isHasMilitaryService());

        return "Hello from savePersonFromWebForm";
    }

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<String> handleException(final NullPointerException ex) {
        log.info("Testing Exception Handler");
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/test")
    public String test(){
        log.info("test");
        return (3/0)+ " is bad";
    }

    @ExceptionHandler({ ArithmeticException .class })
    public ResponseEntity<String> handleArithmeticException (final ArithmeticException  ex) {
        log.info("Testing ArithmeticException Handler");
        String bodyOfResponse = ex.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }



}
