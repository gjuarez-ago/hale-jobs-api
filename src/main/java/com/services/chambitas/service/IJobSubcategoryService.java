package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.JobSubcategory;
import com.services.chambitas.domain.dto.JobSubCategoryDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IJobSubcategoryService {
	
	JobSubcategory createJobSubcategory(JobSubCategoryDTO request) throws GenericException;
	
	JobSubcategory editJobSubcategory(Long id, JobSubCategoryDTO request) throws GenericException;
	
	JobSubcategory deleteJobSubcategory(Long id) throws GenericException;
	
	JobSubcategory findJobSubcategory(Long id) throws GenericException;
	
	List<JobSubcategory> findJobSubcategoryByCategory(Long id) throws GenericException;
	
	Page<JobSubcategory> getAllJobSubcategory(int pageNo, int pageSize);

}
