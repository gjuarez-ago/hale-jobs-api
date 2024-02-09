package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.School;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ISchoolService {

	School deleteSchool(Long id) throws GenericException;
	
	School findSchool(Long id) throws GenericException;
	
	List<School> listByUser(Long user);
	
	School createByUser(School request);

	School updateById(Long id, School request) throws GenericException;

	List<String> getAllSchoolsOnlyKeywords();
	
	Page<School> getAllSchoolsAdmin(int pageNo, int pageSize);
	
}
