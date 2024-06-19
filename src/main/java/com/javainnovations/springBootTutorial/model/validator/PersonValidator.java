package com.javainnovations.springBootTutorial.model.validator;

import com.javainnovations.springBootTutorial.model.pojo.Person;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PersonValidator implements ConstraintValidator<ValidPerson, Person>  {
    @Override
    public boolean isValid(Person person, ConstraintValidatorContext context) {
        if (person == null) {
            return false; // Consider null as not valid
        }

        boolean isValid = true;
        context.disableDefaultConstraintViolation(); // Disable default message

        if (person.isHasMilitaryService()  && person.getAge() < 18) {
            context.buildConstraintViolationWithTemplate("You cannot join the military before the age of 18")
                    .addPropertyNode("age").addConstraintViolation();
            isValid = false;
        } else if (person.isHasMilitaryService()  && person.isFemale()) {
            context.buildConstraintViolationWithTemplate("{myValidation.person.noMilitaryServiceForFemale}")
                    .addPropertyNode("female").addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
