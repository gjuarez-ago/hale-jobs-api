package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.Countries;

public interface ICountriesRepository extends JpaRepository<Countries, Long> {
	
	Countries findCountriesById(Long id);
	
}
