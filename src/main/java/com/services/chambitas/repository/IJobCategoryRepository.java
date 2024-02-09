package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.JobCategory;

public interface IJobCategoryRepository extends JpaRepository<JobCategory, Long> {
	
	JobCategory findJobCategoryById(Long id);
	
}
