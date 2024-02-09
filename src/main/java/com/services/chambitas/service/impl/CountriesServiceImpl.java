package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Countries;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICountriesRepository;
import com.services.chambitas.service.ICountriesService;

@Service
@Transactional
public class CountriesServiceImpl implements ICountriesService{

	@Autowired 
	private ICountriesRepository repository;
	
	@Override
	public Countries createCountry(GenericDTO request) {

		Countries element = new Countries();
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		repository.save(element);
		return element;
	}

	@Override
	public Countries findCountryById(Long id) throws GenericException {
		return existCountry(id);
	}

	@Override
	public List<Countries> getCountries() {
		return repository.findAll();
	}
	
	private Countries existCountry(Long id) throws GenericException {
		
		Countries element = repository.findCountriesById(id);
	
		if(element == null) {
			throw new GenericException("No hemos encontrado el pais");
		}
		
		return element;
	}

}
