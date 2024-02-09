package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IPermissionRepository;
import com.services.chambitas.service.IPermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private IPermissionRepository repository;
		
	@Override
	public List<Permission> getAll() {
		return repository.findAll();
	}

	@Override
	public Permission addPermission(String keyPermission, String description) throws GenericException {
		
		validatePermission(keyPermission);
		Permission entity = new Permission();
		entity.setDescription(description);
		entity.setKeyPermission(keyPermission);
		repository.save(entity);
		
		return entity;
	}

	@Override
	public Permission updatePermission(String currentKeyPermission, String keyPermission, String description) throws GenericException {
		
		Permission entity = existsPermission(currentKeyPermission);
		entity.setDescription(description);
		entity.setKeyPermission(keyPermission);
		repository.save(entity);
		
		return entity;
	}

	@Override
	public Permission deletePermission(String keyPermission) throws GenericException {
		Permission entity = existsPermission(keyPermission);
		repository.deleteById(entity.getId());
		return entity;
	}
	
	@Override
	public Permission findPermissionByKey(String keyPermission) throws GenericException {
		return existsPermission(keyPermission);
	}
	
	@Override
	public Page<Permission> getAllP(int pageNo, int pageSize, String key, String description) {		
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Permission> response = repository.findPermissionsW(key,description, pageable);
		return response;
	}
	
	
	private Permission existsPermission(String keyPermission) throws GenericException {
		
		Permission element = repository.findPermissionByKeyPermission(keyPermission);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	private Permission validatePermission(String keyPermission) throws GenericException {
		
		Permission permission = repository.findPermissionByKeyPermission(keyPermission);
		
		if(permission != null) {throw new GenericException("El permiso ya existe");}	
		
		return permission;
	}


	

}
