package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.File;

public interface IFileRepository extends JpaRepository<File, Long> {
	
	File findFileByConsecutive(String consecutive);
	
}
