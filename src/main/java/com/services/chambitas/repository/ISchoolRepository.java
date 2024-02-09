package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.services.chambitas.domain.School;

public interface ISchoolRepository extends JpaRepository<School,Long>{
	
	School findSchoolById(Long id);
	
	@Query(value = "SELECT s.name FROM school AS s",nativeQuery = true)
	List<String> getAllSchoolKeywords();
	
	@Query(value = "SELECT * FROM school",nativeQuery = true)
	Page<School> getAllSchoolPageable(Pageable pageable);
	
	List<School> findSchoolByUserId(Long userId);


}
