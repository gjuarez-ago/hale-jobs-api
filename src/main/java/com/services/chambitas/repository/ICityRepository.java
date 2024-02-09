package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.CityINEGI;

public interface ICityRepository extends JpaRepository<CityINEGI, Long>{
	
	List<CityINEGI> findCityINEGIByClave(String clave);	
	
	CityINEGI findCityINEGIById(Long id);

}
