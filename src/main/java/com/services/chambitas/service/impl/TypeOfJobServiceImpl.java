package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.dto.TypeOfJobDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ITypeOfJobRepository;
import com.services.chambitas.service.ITypeOfJobService;

@Service
@Transactional
public class TypeOfJobServiceImpl implements ITypeOfJobService{

	@Autowired
	private ITypeOfJobRepository typeOfJobRepository;	
	
	@Override
	public TypeOfJob createTypeOfJob(TypeOfJobDTO request) {
		
		TypeOfJob entity = new TypeOfJob();
		entity.setClave(request.getClave());
		entity.setValor(request.getValor());
		
		typeOfJobRepository.save(entity);
		
		return entity;
	}

	@Override
	public TypeOfJob editTypeOfJob(String id, TypeOfJobDTO request	) throws GenericException {
		
		TypeOfJob entity = existTypeJob(id);
		entity.setClave(request.getClave());
		entity.setValor(request.getValor());
		
		typeOfJobRepository.save(entity);
		
		return entity;
	}

	@Override
	public TypeOfJob deleteTypeOfJob(String id) throws GenericException {

		TypeOfJob entity = existTypeJob(id);
		typeOfJobRepository.deleteById(entity.getId());
		return entity;
	}

	@Override
	public TypeOfJob findTypeOfJob(String id) throws GenericException {
		TypeOfJob entity = existTypeJob(id);
		return entity;
	}

	@Override
	public Page<TypeOfJob> getAllTypeOfJob(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<TypeOfJob> response = typeOfJobRepository.findAll(pageable);
		return response;
	}
	
	@Override
	public List<TypeOfJob> getAll() {
		return typeOfJobRepository.findAll();
	}
	
	// private methods	
	private TypeOfJob existTypeJob(String clave) throws GenericException {
		TypeOfJob element = typeOfJobRepository.findTypeOfJobByClave(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}


	
	
	
	

}
