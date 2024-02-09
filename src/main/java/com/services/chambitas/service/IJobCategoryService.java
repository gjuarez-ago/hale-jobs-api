package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.JobCategory;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IJobCategoryService {

	JobCategory createJobCategory(GenericDTO request);
	
	JobCategory editJobCategory(Long id, GenericDTO request) throws GenericException;
	
	JobCategory deleteJobCategory(Long id) throws GenericException;
	
	JobCategory findJobCategory(Long id) throws GenericException;
	
	List<JobCategory> getAllCategories();
	
	Page<JobCategory> getAllJobCategory(int pageNo, int pageSize);
	
}
