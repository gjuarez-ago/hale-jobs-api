package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Skills;

public interface ISkillsRepository extends JpaRepository<Skills, Long> {

	Skills findSkillsById(Long id);
		
	@Query(value = "SELECT * FROM skills",nativeQuery = true)
	Page<Skills> getSkillsByAdmin(Pageable pageable);
	
	List<Skills> findSkillsByUserId(Long user);
	
	@Modifying
	@Query(value = "DELETE FROM skills WHERE user_id = :id",nativeQuery = true)
	int deleteSkillsByUser(@Param("id") Long id);

}
