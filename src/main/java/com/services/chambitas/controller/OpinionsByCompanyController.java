package com.services.chambitas.controller;

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

import com.services.chambitas.domain.OpinionsCompany;
import com.services.chambitas.domain.dto.OpinionsCompanyDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IOpinionsCompanyService;

@RestController
@RequestMapping(path = { "/opinions-company" })
public class OpinionsByCompanyController {

	@Autowired
	private IOpinionsCompanyService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<OpinionsCompany> createOpinionsCompany(@RequestBody OpinionsCompanyDTO request) throws GenericException {
		OpinionsCompany response = service.createOpinionsCompany(request);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<OpinionsCompany> updateOpinionsCompany(@PathVariable(value = "id") Long id,@RequestBody OpinionsCompanyDTO request) throws GenericException {
		OpinionsCompany response = service.updateOpinionsCompany(id, request);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<OpinionsCompany> findOpinionById(@PathVariable(value = "id") Long id) throws GenericException {
		OpinionsCompany response = service.findOpinionById(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<OpinionsCompany> deleteOpinionById(@PathVariable(value = "id") Long id) throws GenericException {
		OpinionsCompany response = service.deleteOpinionById(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	@GetMapping("/find-by-company")
	public ResponseEntity<Page<OpinionsCompany>> getOpinionsByCompay(
			@RequestParam(value = "companyId", defaultValue = "0", required = false) Long companyId,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
			) {
    	Page<OpinionsCompany> response = service.getOpinionsByCompay(companyId, keyword, pageNo, pageSize);
    	return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	
}
