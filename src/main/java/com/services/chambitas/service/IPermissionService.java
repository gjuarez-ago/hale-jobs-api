package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IPermissionService {
	
	List<Permission> getAll();
		
	Page<Permission> getAllP(int pageNo, int pageSize, String key, String description);
		
	Permission addPermission(String keyPermission, String description) throws GenericException;
		
	Permission updatePermission(String currentKeyPermission,String keyPermission, String description) throws GenericException;
		
	Permission deletePermission(String keyPermission) throws GenericException;
		
	Permission findPermissionByKey(String keyPermission) throws GenericException;	
	
}