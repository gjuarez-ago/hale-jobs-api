package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.StateINEGI;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IStateRepository;
import com.services.chambitas.service.IStateService;

@Service
@Transactional
public class StateServiceImpl implements IStateService {
	
	@Autowired
	private IStateRepository repository;
	
	@Override
	public StateINEGI addState(StateINEGI request) {
		
		StateINEGI entity = new StateINEGI();
		entity.setCountry(request.getCountry());
		entity.setClave(request.getClave());
		entity.setValor(request.getValor());
		repository.save(entity);
		return entity;
	}

	@Override
	public StateINEGI updateState(String key, StateINEGI request) throws GenericException {
		
		StateINEGI entity = validaState(key);
		entity.setCountry(request.getCountry());
		entity.setClave(request.getClave());
		entity.setValor(request.getValor());
		repository.save(entity);
		
		return entity;
	}

	@Override
	public StateINEGI deleteState(String key) throws GenericException {
		StateINEGI entity = validaState(key);
		repository.deleteById(entity.getId());
		return entity;
	}

	@Override
	public List<StateINEGI> getAll() {
		return repository.findAll();
	}

	@Override
	public StateINEGI viewStateByKey(String clave) throws GenericException {
		return validaState(clave);
	}
	
	
	private StateINEGI validaState(String clave) throws GenericException {
		
		StateINEGI element = repository.findStateINEGIByClave(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	

	
	

}
