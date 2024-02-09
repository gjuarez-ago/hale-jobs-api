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

import com.services.chambitas.domain.WorkExperiences;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IWorkExperiencesService;

@RestController
@RequestMapping(path = { "/work-experiences" })
public class WorkExperiencesController {
	
	@Autowired
	private IWorkExperiencesService service;

	@GetMapping("/keywords")
	public ResponseEntity<List<WorkExperiences>> getSkillsGeneralOnlyKeywords() {
		List<WorkExperiences> response = service.getExperiencesGeneralOnlyKeywords();
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/get-by-admin")
	public ResponseEntity<Page<WorkExperiences>> getSkillsByAdmin(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
        ) {
		Page<WorkExperiences> response = service.getExperiencesByAdmin(pageNo, pageSize);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/get-by-user")
	public ResponseEntity<List<WorkExperiences>> listByUser(Long userId) {
		List<WorkExperiences> response = service.listByUser(userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<WorkExperiences> createByUser(@RequestBody WorkExperiences request) {
		WorkExperiences response = service.createByUser(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<WorkExperiences> updateById(@PathVariable(value = "id") Long id, @RequestBody WorkExperiences request) throws GenericException {
		WorkExperiences response = service.updateById(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<WorkExperiences> deleteById(@PathVariable(value = "id") Long id) throws GenericException {
		WorkExperiences response = service.deleteById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<WorkExperiences> findById(@PathVariable(value = "id") Long id) throws GenericException {
		WorkExperiences response = service.findById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}



}
