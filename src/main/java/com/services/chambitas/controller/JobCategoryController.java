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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.JobCategory;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IJobCategoryService;

@RestController
@RequestMapping(path = {"/job-category"})
public class JobCategoryController {

	@Autowired
	private IJobCategoryService service;
	
	@PostMapping("/create")
	public ResponseEntity<JobCategory> createJobCategory(@RequestBody GenericDTO request) {
		JobCategory response = service.createJobCategory(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
    @PutMapping("/edit/{id}")
	public ResponseEntity<JobCategory> editJobCategory(@PathVariable(value="id") Long id, @RequestBody GenericDTO request) throws GenericException {
		JobCategory response = service.createJobCategory(request);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
    
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<JobCategory> deleteJobCategory( @PathVariable(value="id") Long id) throws GenericException {
		JobCategory response = service.deleteJobCategory(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @GetMapping("/find/{id}")
	public ResponseEntity<JobCategory> findJobCategory(@PathVariable(value="id") Long id) throws GenericException {
		JobCategory response = service.findJobCategory(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @GetMapping("/get-all")
	public ResponseEntity<List<JobCategory>> getAllCategories() {
    	List<JobCategory> response = service.getAllCategories();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @GetMapping("/get-all-admin")
	public ResponseEntity<Page<JobCategory>> getAllJobCategory(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
	) {
    	Page<JobCategory> response = service.getAllJobCategory(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	

}
