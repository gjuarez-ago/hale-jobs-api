package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Certification;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICertificationRepository;
import com.services.chambitas.service.ICertificationService;

@Service
@Transactional
public class CertificationServiceImpl implements ICertificationService{
	
	@Autowired
	ICertificationRepository repository;

	@Override
	public List<Certification> listByUser(Long user) {
		return repository.findCertificationByUserId(user);
	}

	@Override
	public Certification createByUser(Certification request) {
		
		Certification response = new Certification();
		
		response.setDescription(request.getDescription());
		response.setName(request.getName());
		response.setUrl(request.getUrl());
		response.setUserId(request.getUserId());
		
		repository.save(response);
		
		return response;
	}

	@Override
	public Certification updateByUser(Long id, Certification request) throws GenericException {
		
		Certification response = exist(id);
		
		response.setDescription(request.getDescription());
		response.setName(request.getName());
		response.setUrl(request.getUrl());
		
		repository.save(response);
		
		return response;
	}

	@Override
	public Certification deleteById(Long id) throws GenericException {
		Certification element = exist(id);
		repository.deleteById(element.getId());
		return element;
	}

	@Override
	public Certification findById(Long id) throws GenericException {
		return exist(id);
	}

	private Certification exist(Long id) throws GenericException {
		
		Certification element = repository.findCertificationById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}

}
