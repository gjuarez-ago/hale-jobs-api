package com.services.chambitas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.services.chambitas.domain.LevelStudy;

public interface ILevelStudyRepository extends JpaRepository<LevelStudy, Long> {

	LevelStudy findLevelStudyById(Long id);

	@Query(value = "SELECT * FROM level_study", nativeQuery = true)
	Page<LevelStudy> getAllLevels(Pageable pageable);

}
