package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.WorkExperiences;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IWorkExperiencesService {
	
    List<WorkExperiences> listByUser(Long userId);
	
    WorkExperiences createByUser(WorkExperiences request);
	
    WorkExperiences updateById(Long id, WorkExperiences request) throws GenericException;
	
    WorkExperiences deleteById(Long id) throws GenericException;
	
    WorkExperiences findById(Long id) throws GenericException;

	List<WorkExperiences> getExperiencesGeneralOnlyKeywords();
	
	Page<WorkExperiences> getExperiencesByAdmin(int pageNo, int pageSize);

}
