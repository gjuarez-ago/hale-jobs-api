package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.Payment;
import com.services.chambitas.domain.dto.PaymentDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IPaymentService;

@RestController
@RequestMapping(path = {"/payment"})
public class PaymentController {
	
	@Autowired
	IPaymentService service;
	
	@GetMapping("/list-m/{key}")
    public ResponseEntity<List<Payment>> getAllPaymentsByLoanM(@PathVariable("key") String loanId) {
		List<Payment> response = service.getPaymentByLoanIdM(loanId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	@GetMapping("/list-w")
    public ResponseEntity<Page<Payment>> getAllPaymentsByLoanW(
    		@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "userId", defaultValue = "", required = false) Long userId) {
		Page<Payment> response = service.getPaymentsByUser(pageNo, pageSize, userId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	@GetMapping("/list-wa")
    public ResponseEntity<Page<Payment>> getAllPaymentsByLoanWA(
    		@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<Payment> response = service.getPaymentsByAdmin(pageNo, pageSize);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	

	@GetMapping("/find/{key}")
	public ResponseEntity<Payment> getPaymentByKey(@PathVariable("key") String key) throws GenericException  {
		Payment response = service.viewPaymentByConsecutive(key);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	 
	@PostMapping("/add")
	public ResponseEntity<Payment> addPayment(@RequestBody PaymentDTO request)  
	{
	   Payment response = service.generatePayment(request);	
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
		 
	
}
