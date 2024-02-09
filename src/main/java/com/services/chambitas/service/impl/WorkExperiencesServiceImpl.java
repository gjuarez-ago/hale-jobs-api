package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.WorkExperiences;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IWorkExperiencesRepository;
import com.services.chambitas.service.IWorkExperiencesService;

@Service
@Transactional
public class WorkExperiencesServiceImpl implements IWorkExperiencesService{

	@Autowired
	private IWorkExperiencesRepository repository;

	@Override
	public List<WorkExperiences> getExperiencesGeneralOnlyKeywords() {
		return repository.findAll();
	}

	@Override
	public Page<WorkExperiences> getExperiencesByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<WorkExperiences> response = repository.findAll(pageable);
		return response;	
	}

	@Override
	public List<WorkExperiences> listByUser(Long userId) {
		return repository.findWorkExperiencesByUserId(userId);
	}

	@Override
	public WorkExperiences createByUser(WorkExperiences request) {
		
		WorkExperiences element = new WorkExperiences();
		
		element.setBegins(request.getBegins());
		element.setCompany(request.getCompany());
		element.setCurrentlyWork(request.isCurrentlyWork());
		element.setDescription(request.getDescription());
		element.setEnds(request.getEnds());
		element.setJob(request.getJob());
		element.setUserId(request.getUserId());
		element.setSkills(request.getSkills());
		element.setWorked(request.isWorked());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public WorkExperiences updateById(Long id, WorkExperiences request) throws GenericException {
		
		WorkExperiences element = exist(id);
		
		element.setBegins(request.getBegins());
		element.setCompany(request.getCompany());
		element.setCurrentlyWork(request.isCurrentlyWork());
		element.setDescription(request.getDescription());
		element.setEnds(request.getEnds());
		element.setJob(request.getJob());
		element.setSkills(request.getSkills());
		element.setWorked(request.isWorked());

		repository.save(element);

		return element;
	}

	@Override
	public WorkExperiences deleteById(Long id) throws GenericException {
		
		WorkExperiences element = exist(id);
		repository.deleteById(element.getId());
		return element;
	}

	@Override
	public WorkExperiences findById(Long id) throws GenericException {
		return exist(id);
	}
	
	private WorkExperiences exist(Long id) throws GenericException {
		
		WorkExperiences element = repository.findWorkExperiencesById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	
	
}
