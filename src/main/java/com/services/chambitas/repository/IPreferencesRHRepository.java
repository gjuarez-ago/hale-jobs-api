package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.PreferencesRH;

public interface IPreferencesRHRepository extends JpaRepository<PreferencesRH, Long>{

	@Query(value = "SELECT p FROM PreferencesRH p where p.userId = :id")
	PreferencesRH getPreferencesRHByUser(@Param("id") Long id);

}
