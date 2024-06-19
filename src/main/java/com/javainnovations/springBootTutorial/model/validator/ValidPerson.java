package com.javainnovations.springBootTutorial.model.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PersonValidator.class)
@Target({ TYPE})
@Retention(RUNTIME)
public @interface ValidPerson {
    String message() default "Person is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}