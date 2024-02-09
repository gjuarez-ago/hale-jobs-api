package com.services.chambitas.controller;

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

import com.services.chambitas.domain.Complaints;
import com.services.chambitas.domain.dto.ComplaintDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IComplaintService;

@RestController
@RequestMapping(path = { "/complaint" })
public class ComplaintController {

	@Autowired
	IComplaintService service;

	@GetMapping("/view-admin")
	public ResponseEntity<Page<Complaints>> getAllCommentsAdmin(@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<Complaints> response = service.getAllComplaintsByAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/view-rh")
	public ResponseEntity<Page<Complaints>> getAllComplaintsOfferByUserRH(@RequestParam("user") Long userId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<Complaints> response = service.getAllComplaintsOfferByUserRH(userId, pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/view-complaints-by-offer")
	public ResponseEntity<Page<Complaints>> getAllComplaintsOfferByOffer(
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam("offerId") Long offerId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<Complaints> response = service.getAllComplaintsOfferByOffer(keyword, offerId, pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	
	@PostMapping("/create")
	public ResponseEntity<Complaints> createComplaint(@RequestBody ComplaintDTO request) throws GenericException {
		Complaints response = service.createComplaint(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Complaints> deleteComplaint(@PathVariable(value = "id") Long id) throws GenericException {
		Complaints response = service.deleteComplaint(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
