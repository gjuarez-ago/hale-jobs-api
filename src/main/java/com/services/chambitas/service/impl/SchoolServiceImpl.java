package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.School;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ISchoolRepository;
import com.services.chambitas.service.ISchoolService;

@Service
@Transactional
public class SchoolServiceImpl implements ISchoolService{

    @Autowired
	private ISchoolRepository repository;
	 
	@Override
	public School deleteSchool(Long id) throws GenericException {
		
		School element = existSchool(id);
		repository.deleteById(id);
		return element;
	}

	@Override
	public School findSchool(Long id) throws GenericException {
		return existSchool(id);
	}
	

	@Override
	public List<School> listByUser(Long user) {
		return repository.findSchoolByUserId(user);
	}

	@Override
	public School createByUser(School request) {
	   
		School element = new School();
		
		element.setBegins(request.getBegins());
		element.setEnds(request.getEnds());
		element.setName(request.getName());
		element.setType(request.getType());
		element.setSchoolName(request.getSchoolName());
		element.setUserId(request.getUserId());
		element.setWorked(request.isWorked());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public School updateById(Long id, School request) throws GenericException {
		
		School element = existSchool(id);
		
		element.setBegins(request.getBegins());
		element.setEnds(request.getEnds());
		element.setName(request.getName());
		element.setType(request.getType());
		element.setSchoolName(request.getSchoolName());
		element.setWorked(request.isWorked());

		repository.save(element);

		return element;
	}

	@Override
	public List<String> getAllSchoolsOnlyKeywords() {
		return repository.getAllSchoolKeywords();
	}

	@Override
	public Page<School> getAllSchoolsAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<School> response = repository.getAllSchoolPageable(pageable);
		return response;
	}
		
	private School existSchool(Long id) throws GenericException {
			
		School element = repository.findSchoolById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}

	
	

}
