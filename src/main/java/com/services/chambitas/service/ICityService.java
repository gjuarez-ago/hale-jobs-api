package com.services.chambitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.services.chambitas.domain.CityINEGI;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ICityService {
	
	CityINEGI addCity(CityINEGI request);
	
	CityINEGI updateCity(long id, CityINEGI request) throws GenericException;
	
	List<CityINEGI> getCities();
	
	List<CityINEGI> getCitiesByState(String state);
	
	CityINEGI viewCityByKey(long id) throws GenericException;
	
	CityINEGI deleteCity(long id) throws GenericException;
	
}
