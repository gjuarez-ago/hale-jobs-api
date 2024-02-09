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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IPermissionService;

@RestController
@RequestMapping(path = {"/permission"})
public class PermissionController {
	
	@Autowired
	IPermissionService service;
	
	@GetMapping("/list")
    public ResponseEntity<List<Permission>> getAllPermissions() {
		List<Permission> response = service.getAll();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }		
	
	@GetMapping("/list-p")
    public ResponseEntity<Page<Permission>> getAllPermissionsPaginate(
    		@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "key", defaultValue = "", required = false) String key,
            @RequestParam(value = "description", defaultValue = "", required = false) String description
    		) {
		Page<Permission> response = service.getAllP(pageNo, pageSize, key, description);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }		

	@PostMapping("/add")
	public ResponseEntity<Permission> addPermission(@RequestParam("keyPermission") String key,@RequestParam("description") String description) throws GenericException  {
		Permission response = service.addPermission(key, description);
	        return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping("/find/{key}")
	public ResponseEntity<Permission> findPermissionByKey(@PathVariable(value = "key") String key) throws GenericException  
	{
	   Permission response =  service.findPermissionByKey(key);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Permission> updatePermission(@RequestParam("currentKeyPermission") String currentKeyPermission,@RequestParam("keyPermission") String keyPermission,@RequestParam("description") String description) throws GenericException  
	{
	   Permission response =  service.updatePermission(currentKeyPermission, keyPermission, description);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	 
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<Permission> deletePermission(@PathVariable(value = "key") String currentPermission) throws GenericException
	{
		Permission response = service.deletePermission(currentPermission);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}	
	
	
	
}

