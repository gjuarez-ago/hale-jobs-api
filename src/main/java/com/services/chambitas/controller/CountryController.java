package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.Countries;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ICountriesService;

@RestController
@RequestMapping(path = {"/country"})
public class CountryController{
	
	@Autowired
	private ICountriesService service;

	@PostMapping("/create")
	public ResponseEntity<Countries> createCountry(@RequestBody GenericDTO request) {
		Countries response = service.createCountry(request);		
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Countries> findCountryById(@PathVariable(value = "id") Long id) throws GenericException {
		Countries response = service.findCountryById(id);		
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<Countries>> getCountries() {
		List<Countries> response = service.getCountries();		
		return new ResponseEntity<>(response , HttpStatus.OK);
	}
	

}
