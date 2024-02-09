package com.services.chambitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Languages;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ILanguagesService {
	
	List<Languages> listByUser(Long user);
	
	Languages createByUser(Languages request);
	
	Languages updateByUser(Long id, Languages request)  throws GenericException;
	
	Languages deleteByUser(Long id)  throws GenericException;
	
	Languages findById(Long id)  throws GenericException;
	
}
