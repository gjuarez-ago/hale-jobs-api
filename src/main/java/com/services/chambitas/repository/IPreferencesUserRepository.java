package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.PreferencesUser;

public interface IPreferencesUserRepository extends JpaRepository<PreferencesUser, Long>{
	
	

}
