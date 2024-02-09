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

import com.services.chambitas.domain.LevelStudy;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ILevelStudyService;

@RestController
@RequestMapping(path = {"/level-study"})
public class LevelStudyController{
	
	 @Autowired
	 private ILevelStudyService service;

	
    @GetMapping("/find/{id}")
	public ResponseEntity<LevelStudy> findLevelStudyById(@PathVariable(value = "id") Long id) throws GenericException {
    	LevelStudy response = service.findLevelStudyById(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
    }

    @PostMapping("/create")
	public ResponseEntity<LevelStudy>  createLevelStudy(@RequestBody GenericDTO request) {
    	LevelStudy response = service.createLevelStudy(request);
    return new ResponseEntity<>(response, HttpStatus.OK);	
	}

    
	@PutMapping("/update/{id}")
	public ResponseEntity<LevelStudy>  updateLevelStudy(@PathVariable(value = "id") Long id,@RequestBody GenericDTO request) {
    	LevelStudy response = service.createLevelStudy(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
   }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<LevelStudy>  deleteLevelStudyById(Long id) throws GenericException {
    	LevelStudy response = service.deleteLevelStudyById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<LevelStudy> > getLevelsGeneral() {
    	List<LevelStudy> response = service.getLevelsGeneral();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get-by-admin")
	public ResponseEntity<Page<LevelStudy>> getLevelStudyAdmin(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
    	Page<LevelStudy> response = service.getLevelStudyAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	 
	 

}
