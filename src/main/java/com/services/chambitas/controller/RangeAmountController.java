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

import com.services.chambitas.domain.RangeAmount;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IRangeAmountService;

@RestController
@RequestMapping(path = { "/range-amount" })
public class RangeAmountController {

	
	@Autowired
	private IRangeAmountService service;

	@PostMapping("/create")
	public ResponseEntity<RangeAmount> createRangeAmount(@RequestBody GenericDTO request) {
		RangeAmount response = service.createRangeAmount(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<RangeAmount> editRangeAmount(@PathVariable(value = "id") Long id,@RequestBody GenericDTO request) throws GenericException {
		RangeAmount response = service.editRangeAmount(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RangeAmount> deleteRangeAmount(@PathVariable(value = "id")  Long id) throws GenericException {
		RangeAmount response = service.deleteRangeAmount(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<RangeAmount> findRangeAmount(@PathVariable(value = "id")  Long id) throws GenericException {
		RangeAmount response = service.findRangeAmount(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<RangeAmount>> getAllRangeAmount() {
		List<RangeAmount> response = service.getAllRangeAmount();
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("get-by-admin")
	public ResponseEntity<Page<RangeAmount>> getAllRangeAmountAdmin(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<RangeAmount> response = service.getAllRangeAmountAdmin(pageNo, pageSize);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}	

}
