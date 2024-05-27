package com.javainnovations.springBootTutorial.controller;


import com.javainnovations.springBootTutorial.model.pojo.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    public String savePersonFromForm(@RequestParam String name,
                                     @RequestParam int age, @RequestParam boolean hasMilitaryService)
    {
        log.info("Hello from savePersonFromForm");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);

        return "Hello from savePersonFromWebForm";
    }

    @PostMapping("/savePersonFromPostForm")
    public String savePersonFromPostForm(@RequestParam String name,
                                         @RequestParam int age, @RequestParam boolean hasMilitaryService)
    {
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

    @GetMapping("/savePersonFromPathParam/{name}/{age}/{hasMilitaryService}")
    public String savePersonFromPathParam(@PathVariable("name") String name,
                                          @PathVariable("age") int age,
                                          @PathVariable("hasMilitaryService") boolean hasMilitaryService){
        log.info("Hello from savePersonFromPathParam");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        return "Hello from savePersonFromPathParam";
    }

    @GetMapping("/savePersonFromHeaderParam")
    public String savePersonFromHeaderParam(@RequestHeader(value = "name") String name,
                                            @RequestHeader(value = "age") int age,
                                            @RequestHeader(value = "hasMilitaryService") boolean hasMilitaryService){
        log.info("Hello from savePersonFromHeaderParam");
        log.info("Name: " + name);
        log.info("Age: " + age);
        log.info("Has Military Service: " + hasMilitaryService);
        return "Hello from savePersonFromHeaderParam";
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
