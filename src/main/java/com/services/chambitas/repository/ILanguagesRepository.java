package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.Languages;

public interface ILanguagesRepository  extends JpaRepository<Languages, Long> {

	List<Languages> findLanguagesByUserId(Long user);
	
	Languages findLanguagesById(Long id);	
}
