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

import com.services.chambitas.domain.TypeOfPayment;
import com.services.chambitas.domain.dto.TypeOfPaymentDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ITypeOfPaymentService;

@RestController
@RequestMapping(path = { "/type-of-payment" })
public class TypeOfPaymentController {

	@Autowired
	ITypeOfPaymentService service;

	@PostMapping("/create")
	public ResponseEntity<TypeOfPayment> createTypeOfPayment(@RequestBody TypeOfPaymentDTO request) throws GenericException {
		TypeOfPayment response = service.createTypeOfPayment(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/edit/{key}")
	public ResponseEntity<TypeOfPayment> editTypeOfPayment(@PathVariable("key") String id, @RequestBody TypeOfPaymentDTO request)
			throws GenericException {
		TypeOfPayment response = service.editTypeOfPayment(id, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{key}")
	public ResponseEntity<TypeOfPayment> editTypeOfPayment(@PathVariable("key") String id) throws GenericException {
		TypeOfPayment response = service.deleteTypeOfPayment(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/find/{key}")
	public ResponseEntity<TypeOfPayment> findTypeOfPayment(@PathVariable("key") String id) throws GenericException {
		TypeOfPayment response = service.findTypeOfPayment(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@GetMapping("/get-all")
	public ResponseEntity<List<TypeOfPayment>> getAllPaymentsAdmin() {

		List<TypeOfPayment> response = service.getAllTypeOfPayments();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/view-admin")
	public ResponseEntity<Page<TypeOfPayment>> getAllPaymentsAdmin(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

		Page<TypeOfPayment> response = service.getAllTypeOfPaymentsByAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
