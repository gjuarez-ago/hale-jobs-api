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

import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.dto.TypeOfJobDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ITypeOfJobService;

@RestController
@RequestMapping(path = {"/type-of-job"})
public class TypeOfJobController {

	
	@Autowired
	ITypeOfJobService service;
	
	// Crear tipo de trabajo
	@PostMapping("/create")
    public ResponseEntity<TypeOfJob> createTypeOfJob(@RequestBody TypeOfJobDTO request) throws GenericException {
		TypeOfJob response = service.createTypeOfJob(request);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	// Editar tipo de trabajo
	@PostMapping("/edit/{key}")
	public ResponseEntity<TypeOfJob> editTypeOfJob(@PathVariable("key") String id,@RequestBody TypeOfJobDTO request) throws GenericException {
		TypeOfJob response = service.editTypeOfJob(id, request);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	// Eliminar tipo de trabajo
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<TypeOfJob> deleteJob(@PathVariable("key") String id) throws GenericException {
		TypeOfJob response = service.deleteTypeOfJob(id);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	@GetMapping("/get-all")
	public ResponseEntity<List<TypeOfJob>> findTypeOfJob() {
		List<TypeOfJob> response = service.getAll();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	
	// Buscar tipo de trabjo
	@GetMapping("/find/{key}")
	public ResponseEntity<TypeOfJob> findTypeOfJob(@PathVariable("key") String id) throws GenericException {
		TypeOfJob response = service.findTypeOfJob(id);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
	
	// Obtener listado de tipos de trabajo
	@GetMapping("/view-admin")
	public ResponseEntity<Page<TypeOfJob>> getAll(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<TypeOfJob> response = service.getAllTypeOfJob(pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
	
	
}
