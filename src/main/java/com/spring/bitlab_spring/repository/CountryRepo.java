package com.spring.bitlab_spring.repository;

import com.spring.bitlab_spring.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Countries, Long> {

}
