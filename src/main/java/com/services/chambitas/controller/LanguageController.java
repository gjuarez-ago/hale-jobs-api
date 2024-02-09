package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.Languages;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ILanguagesService;


@RestController
@RequestMapping(path = {"/language"})
public class LanguageController {

	@Autowired
	ILanguagesService service;

	@GetMapping("/get-by-user")
	public ResponseEntity<List<Languages>> listByUser(Long userId) {
		List<Languages> response = service.listByUser(userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Languages> createByUser(@RequestBody Languages request) {
		Languages response = service.createByUser(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Languages> updateById(@PathVariable(value = "id") Long id, @RequestBody Languages request) throws GenericException {
		Languages response = service.updateByUser(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Languages> deleteById(@PathVariable(value = "id") Long id) throws GenericException {
		Languages response = service.deleteByUser(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Languages> findById(@PathVariable(value = "id") Long id) throws GenericException {
		Languages response = service.findById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
}
