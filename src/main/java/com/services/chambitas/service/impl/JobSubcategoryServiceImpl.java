package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.JobCategory;
import com.services.chambitas.domain.JobSubcategory;
import com.services.chambitas.domain.dto.JobSubCategoryDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IJobCategoryRepository;
import com.services.chambitas.repository.IJobSubcategoryRepository;
import com.services.chambitas.service.IJobSubcategoryService;

@Service
@Transactional
public class JobSubcategoryServiceImpl  implements IJobSubcategoryService{

	
	@Autowired 
	private IJobSubcategoryRepository repository;
	
	@Autowired
	private IJobCategoryRepository categoryRepository;

	@Override
	public JobSubcategory createJobSubcategory(JobSubCategoryDTO request) throws GenericException {
		
		JobCategory category = existCategory(request.getId());
		JobSubcategory element = new JobSubcategory();
		
		element.setCategory(category);
		element.setValor(request.getClave());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public JobSubcategory editJobSubcategory(Long id, JobSubCategoryDTO request) throws GenericException {
		
		JobCategory category = existCategory(request.getId());
		JobSubcategory element = existSubcategory(id);
		
		element.setCategory(category);
		element.setValor(request.getClave());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public JobSubcategory deleteJobSubcategory(Long id) throws GenericException {
		JobSubcategory element = existSubcategory(id);
		repository.deleteById(id);
		return element;
	}

	@Override
	public JobSubcategory findJobSubcategory(Long id) throws GenericException {
		return existSubcategory(id);
	}

	@Override
	public List<JobSubcategory> findJobSubcategoryByCategory(Long id) {
		 return repository.getAllByCategory(id);
	}

	@Override
	public Page<JobSubcategory> getAllJobSubcategory(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<JobSubcategory> response = repository.findAll(pageable);
		return response;	
	}
		
	private JobSubcategory existSubcategory(Long id) throws GenericException {
		JobSubcategory element = repository.findJobSubcategoryById(id);
		if(element == null) {throw new GenericException("No hemos encontrado la subcategoría");}
		return element;
	}
	
	private JobCategory existCategory(Long id) throws GenericException {
		
		JobCategory element = categoryRepository.findJobCategoryById(id);
	     
		if(element == null) {
			throw new GenericException("No hemos encontrado la subcategoría");
		}
		
		return element;
		
		
	}

	
	

}
