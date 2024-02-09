package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.CityINEGI;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICityRepository;
import com.services.chambitas.service.ICityService;

@Service
@Transactional
public class CityServiceImpl implements ICityService{
	
	@Autowired
	private ICityRepository repository;
	
	@Override
	public CityINEGI addCity(CityINEGI request) {
		
	   CityINEGI entity = new CityINEGI();
	    entity.setClave(request.getClave());
	    entity.setValor(request.getValor());
	    repository.save(entity);
	
		return entity;
	}

	@Override
	public CityINEGI updateCity(long id, CityINEGI request) throws GenericException {
		CityINEGI entity = validateCity(id);
		entity.setClave(request.getClave());
		entity.setValor(request.getValor());
		repository.save(entity);
		return entity;
	}

	@Override
	public List<CityINEGI> getCities() {
		return repository.findAll();
	}

	@Override
	public CityINEGI deleteCity(long id) throws GenericException {
		CityINEGI entity = validateCity(id);
		repository.deleteById(id);
		return entity;
	}

	@Override
	public CityINEGI viewCityByKey(long id) throws GenericException {
		return validateCity(id);
	}
	
	@Override
	public List<CityINEGI> getCitiesByState(String state) {
		return repository.findCityINEGIByClave(state);
	}
	
	
	private CityINEGI validateCity(long id) throws GenericException {
		
	     CityINEGI entity = repository.findCityINEGIById(id);
	     if(entity == null) {
				throw new GenericException("No se encontro el recurso");
	     }
		
		return entity;
	}

	

	
}
