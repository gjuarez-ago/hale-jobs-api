package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.School;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ISchoolService;

@RestController
@RequestMapping(path = { "/school" })
public class SchoolController {
	
	@Autowired
	private ISchoolService service;
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<School> deleteSchool(@PathVariable(value = "id") Long id) throws GenericException {
		School response = service.deleteSchool(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<School> findSchool(@PathVariable(value = "id") Long id) throws GenericException {
		School response = service.findSchool(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<String>> getAllSchoolsOnlyKeywords() {
		List<String> response = service.getAllSchoolsOnlyKeywords();
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	@GetMapping("/get-by-admin")
	public ResponseEntity<Page<School>> getAllSchoolsAdmin(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<School> response = service.getAllSchoolsAdmin(pageNo, pageSize);
    	return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get-by-user")
	public ResponseEntity<List<School>> listByUser(Long userId) {
		List<School> response = service.listByUser(userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<School> createByUser(@RequestBody School request) {
		School response = service.createByUser(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<School> updateById(@PathVariable(value = "id") Long id, @RequestBody School request) throws GenericException {
		School response = service.updateById(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}


}
