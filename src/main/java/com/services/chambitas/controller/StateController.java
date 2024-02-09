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

import com.services.chambitas.domain.StateINEGI;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IStateService;

@RestController
@RequestMapping(path = {"/state"})
public class StateController {
	
	@Autowired
	IStateService service;

	@GetMapping("/list")
    public ResponseEntity<List<StateINEGI>> getAllCities() {
		List<StateINEGI> response = service.getAll();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	@GetMapping("/find/{key}")
	public ResponseEntity<StateINEGI> getCityByKey(@PathVariable("key") String id) throws GenericException  {
		StateINEGI response = service.viewStateByKey(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<StateINEGI> addCity(@RequestBody StateINEGI request)  
	{
		StateINEGI response = service.addState(request);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update/{key}")
	public ResponseEntity<StateINEGI> updateCity(@PathVariable("key") String id,@RequestBody StateINEGI request) throws GenericException {
		StateINEGI response = service.updateState(id, request);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<StateINEGI> deleteTypeExpense(@PathVariable("key") String id) throws GenericException
	{
		StateINEGI response = service.deleteState(id);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}