package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Languages;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ILanguagesRepository;
import com.services.chambitas.service.ILanguagesService;

@Service
@Transactional
public class LanguagesServiceImpl implements ILanguagesService{
		
	@Autowired
	ILanguagesRepository repository;

	@Override
	public List<Languages> listByUser(Long user) {
		return repository.findLanguagesByUserId(user);
	}

	@Override
	public Languages createByUser(Languages request) {
		
		Languages response = new Languages();
		
		response.setLevel(request.getLevel());
		response.setName(request.getName());
		response.setUserId(request.getUserId());
		
		repository.save(response);

		
		return response;
	}

	@Override
	public Languages updateByUser(Long id, Languages request) throws GenericException {
		
		Languages response = exist(id);
		
		response.setLevel(request.getLevel());
		response.setName(request.getName());
		
		repository.save(response);
		
		return response;
	}

	@Override
	public Languages deleteByUser(Long id) throws GenericException {
		Languages response = exist(id);
		repository.deleteById(id);
		return response;
	}

	@Override
	public Languages findById(Long id) throws GenericException {
		return exist(id);
	}
	
    private Languages exist(Long id) throws GenericException {
		
    	Languages element = repository.findLanguagesById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}

}
