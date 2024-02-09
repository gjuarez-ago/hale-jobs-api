package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.WorkExperiences;

public interface IWorkExperiencesRepository extends JpaRepository<WorkExperiences, Long> {
	
	WorkExperiences findWorkExperiencesById(Long id);
	
	List<WorkExperiences> findWorkExperiencesByUserId(Long userId);
		
}
