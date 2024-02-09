package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.JobCategory;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IJobCategoryRepository;
import com.services.chambitas.service.IJobCategoryService;

@Service
@Transactional
public class JobCategoryServiceImpl implements IJobCategoryService{

	@Autowired
	private IJobCategoryRepository repository;
	
	@Override
	public JobCategory createJobCategory(GenericDTO request) {
		
		JobCategory response = new JobCategory();
		response.setClave(request.getClave());
		response.setValor(request.getValor());
		repository.save(response);
	
		return response;
	}

	@Override
	public JobCategory editJobCategory(Long id, GenericDTO request) throws GenericException {

		JobCategory response = existCategory(id);
		response.setClave(request.getClave());
		response.setValor(request.getValor());
		repository.save(response);
		
		return response;
	}

	@Override
	public JobCategory deleteJobCategory(Long id) throws GenericException {
		JobCategory response = existCategory(id);
		repository.deleteById(id);
		return response;
	}

	@Override
	public JobCategory findJobCategory(Long id) throws GenericException {
		return existCategory(id);
	}

	@Override
	public List<JobCategory> getAllCategories() {
		return repository.findAll();
	}

	@Override
	public Page<JobCategory> getAllJobCategory(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<JobCategory> response = repository.findAll(pageable);
		return response;	
	}
	
	
	private JobCategory existCategory(Long id) throws GenericException {
	
		JobCategory element = repository.findJobCategoryById(id);
	     
		if(element == null) {
			throw new GenericException("No hemos encontrado el pais");
		}
		
		return element;
		
	}
	
	
	
	
	

}
