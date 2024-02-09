package com.services.chambitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Countries;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ICountriesService {
	
	Countries createCountry(GenericDTO request);
	
	Countries findCountryById(Long id) throws GenericException;
	
	List<Countries> getCountries();

}
