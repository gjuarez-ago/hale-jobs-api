package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.JobSubcategory;

public interface IJobSubcategoryRepository extends JpaRepository<JobSubcategory, Long> {

	JobSubcategory findJobSubcategoryById(Long id);
	
	@Query(value = "SELECT * FROM job_subcategory AS js WHERE js.category_id = :category", nativeQuery = true)
	List<JobSubcategory> getAllByCategory(@Param("category") Long category);
	
}
