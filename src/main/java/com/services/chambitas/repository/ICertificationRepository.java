package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.Certification;

public interface ICertificationRepository extends JpaRepository<Certification, Long>{
	
	List<Certification> findCertificationByUserId(Long userId);
	
	Certification findCertificationById(Long id);	
	
}
