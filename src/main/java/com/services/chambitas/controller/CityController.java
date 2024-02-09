package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.CityINEGI;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ICityService;

@RestController
@RequestMapping(path = {"/city"})
public class CityController {
	
	@Autowired
	ICityService service;
	
	@GetMapping("/list")
    public ResponseEntity<List<CityINEGI>> getAllCities() {
		List<CityINEGI> response = service.getCities();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	@GetMapping("/list-by-state/{key}")
    public ResponseEntity<List<CityINEGI>> getAllCitiesByState(@PathVariable("key") String keyState) {
		List<CityINEGI> response = service.getCitiesByState(keyState);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	@GetMapping("/find/{key}")
	public ResponseEntity<CityINEGI> getCityByKey(@PathVariable("key") long id) throws GenericException  {
		CityINEGI response = service.viewCityByKey(id); 
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<CityINEGI> addCity(@RequestBody CityINEGI request)  
	{
		CityINEGI response = service.addCity(request); 
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update/{key}")
	public ResponseEntity<CityINEGI> updateCity(@PathVariable("key") long id,@RequestBody CityINEGI request) throws GenericException {
		CityINEGI response = service.updateCity(id, request);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<CityINEGI> deleteTypeExpense(@PathVariable("key") long id) throws GenericException
	{
		CityINEGI response = service.deleteCity(id);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}

