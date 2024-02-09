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

import com.services.chambitas.domain.JobSubcategory;
import com.services.chambitas.domain.dto.JobSubCategoryDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IJobSubcategoryService;

@RestController
@RequestMapping(path = {"/job-subcategory"})
public class JobSubCategoryController {

	@Autowired
	private IJobSubcategoryService service;

	@PostMapping("/create")
	public ResponseEntity<JobSubcategory> createJobSubcategory(@RequestBody JobSubCategoryDTO request) throws GenericException {
		JobSubcategory response = service.createJobSubcategory(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @PutMapping("/edit/{id}")
	public ResponseEntity<JobSubcategory> editJobSubcategory(@PathVariable(value = "id") Long id,@RequestBody JobSubCategoryDTO request) throws GenericException {
    	JobSubcategory response = service.editJobSubcategory(id, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @DeleteMapping("/delete/{id}")
	public ResponseEntity<JobSubcategory> deleteJobSubcategory(@PathVariable(value = "id") Long id) throws GenericException {
    	JobSubcategory response = service.deleteJobSubcategory(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @GetMapping("/find/{id}")
	public ResponseEntity<JobSubcategory> findJobSubcategory(@PathVariable(value = "id") Long id) throws GenericException {
    	JobSubcategory response = service.findJobSubcategory(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    @GetMapping("/find-by-category/{id}")
	public ResponseEntity<List<JobSubcategory> > findJobSubcategoryByCategory(@PathVariable(value = "id") Long id) throws GenericException {
    	List<JobSubcategory> response = service.findJobSubcategoryByCategory(id);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

    @GetMapping("/get-all-admin")
	public ResponseEntity<Page<JobSubcategory>> getAllJobSubcategory(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
    	Page<JobSubcategory> response = service.getAllJobSubcategory(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	

}
