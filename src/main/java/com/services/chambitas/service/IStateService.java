package com.services.chambitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.services.chambitas.domain.StateINEGI;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IStateService {
	
	 StateINEGI addState(StateINEGI request);
	 
	 StateINEGI updateState(String clave,StateINEGI request) throws GenericException;
	 
	 StateINEGI viewStateByKey(String key) throws GenericException;
	 
	 StateINEGI deleteState(String key) throws GenericException;
	 
	 List<StateINEGI> getAll();

}
