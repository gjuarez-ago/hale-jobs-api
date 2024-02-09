package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.StateINEGI;

public interface IStateRepository extends JpaRepository<StateINEGI, Long>{
	
	StateINEGI findStateINEGIById(Long id);
	
	StateINEGI findStateINEGIByClave(String clave);

}
