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

import com.services.chambitas.domain.Certification;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ICertificationService;

@RestController
@RequestMapping(path = {"/certification"})
public class CertificationController {
	
	
	@Autowired
	ICertificationService service;

	@GetMapping("/get-by-user")
	public ResponseEntity<List<Certification>> listByUser(Long userId) {
		List<Certification> response = service.listByUser(userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Certification> createByUser(@RequestBody Certification request) {
		Certification response = service.createByUser(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Certification> updateById(@PathVariable(value = "id") Long id, @RequestBody Certification request) throws GenericException {
		Certification response = service.updateByUser(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Certification> deleteById(@PathVariable(value = "id") Long id) throws GenericException {
		Certification response = service.deleteById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Certification> findById(@PathVariable(value = "id") Long id) throws GenericException {
		Certification response = service.findById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

}
