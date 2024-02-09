package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.TypeOfJob;

public interface ITypeOfJobRepository extends JpaRepository<TypeOfJob, Long>{
	
      TypeOfJob findTypeOfJobByClave(String clave);
      
      TypeOfJob findTypeOfJobById(Long id);
      
}
