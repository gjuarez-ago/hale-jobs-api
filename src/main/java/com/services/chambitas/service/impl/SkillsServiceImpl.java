package com.services.chambitas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Skills;
import com.services.chambitas.domain.dto.SkillsDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ISkillsRepository;
import com.services.chambitas.service.ISkillsService;

@Service
@Transactional
public class SkillsServiceImpl implements ISkillsService{

	
	@Autowired
	private ISkillsRepository repository;
		
	@Override
	public List<Skills> getSkillsGeneralOnlyKeywords() {
		return repository.findAll();
	}

	@Override
	public Page<Skills> getSkillsByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Skills> response = repository.getSkillsByAdmin(pageable);
		return response;
	}

	@Override
	public List<Skills> listByUser(Long user) {
		return repository.findSkillsByUserId(user);
	}

	@Override
	public Skills createByUser(Skills request) {
		
		Skills element = new Skills();
		
		element.setUserId(request.getUserId());
		element.setValue(request.getValue());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public Skills updateById(Long id, Skills request) throws GenericException  {
	   
		Skills element = exist(id);
		element.setValue(request.getValue());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public Skills deleteById(Long id) throws GenericException {

		Skills element = exist(id);
	    repository.deleteById(id);	
		
		return element;

	}

	@Override
	public Skills findById(Long id) throws GenericException  {
		return exist(id);
	}
	
    private Skills exist(Long id) throws GenericException {
		
    	Skills element = repository.findSkillsById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}

	@Override
	public List<Skills> updateSkillsByUser(SkillsDTO request) {
		
		repository.deleteSkillsByUser(request.getUserId());
		
		List<Skills> s = new ArrayList<>();
		
		for(String skill : request.getSkills()) {
			Skills e = new Skills();
		    e.setValue(skill);
		    e.setUserId(request.getUserId());
		    s.add(e);
		}
		
		repository.saveAll(s);
		
		return s;
	}
	

}
