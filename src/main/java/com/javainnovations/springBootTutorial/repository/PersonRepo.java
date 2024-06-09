package com.javainnovations.springBootTutorial.repository;

import com.javainnovations.springBootTutorial.model.dto.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo  extends JpaRepository<PersonDto, Integer> {
}
