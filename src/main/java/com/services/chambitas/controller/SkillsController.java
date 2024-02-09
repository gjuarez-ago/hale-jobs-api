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

import com.services.chambitas.domain.Skills;
import com.services.chambitas.domain.dto.SkillsDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ISkillsService;

@RestController
@RequestMapping(path = { "/skills" })
public class SkillsController {

	@Autowired
	private ISkillsService service;


	@GetMapping("/list-keys/{id}")
	public ResponseEntity<List<Skills>> getSkillsGeneralOnlyKeywords() {
		List<Skills> response = service.getSkillsGeneralOnlyKeywords();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/list-admin/{id}")
	public ResponseEntity<Page<Skills>> getSkillsByAdmin(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<Skills> response = service.getSkillsByAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/get-by-user")
	public ResponseEntity<List<Skills>> listByUser(Long userId) {
		List<Skills> response = service.listByUser(userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<List<Skills>> createByUser(@RequestBody SkillsDTO request) {
		List<Skills> response = service.updateSkillsByUser(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@PostMapping("/add-skill")
	public ResponseEntity<Skills> createByUser(@RequestBody Skills request) {
		Skills response = service.createByUser(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Skills> updateById(@PathVariable(value = "id") Long id, @RequestBody Skills request) throws GenericException {
		Skills response = service.updateById(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Skills> deleteById(@PathVariable(value = "id") Long id) throws GenericException {
		Skills response = service.deleteById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Skills> findById(@PathVariable(value = "id") Long id) throws GenericException {
		Skills response = service.findById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

}
