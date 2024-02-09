package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.LevelStudy;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ILevelStudyRepository;
import com.services.chambitas.service.ILevelStudyService;

@Service
@Transactional
public class LevelStudyServiceImpl implements ILevelStudyService{

	
	@Autowired
	private ILevelStudyRepository repository;
	
	@Override
	public LevelStudy findLevelStudyById(Long id) throws GenericException {
		return existLevel(id);
	}

	@Override
	public LevelStudy createLevelStudy(GenericDTO request) {
		LevelStudy element = new LevelStudy();
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		repository.save(element);
		return element;
	}

	@Override
	public LevelStudy updateLevelStudy(Long id, GenericDTO request) throws GenericException {
		LevelStudy element = existLevel(id);
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		repository.save(element);
		return element;
	}

	@Override
	public LevelStudy deleteLevelStudyById(Long id) throws GenericException {
		LevelStudy element = existLevel(id);
		repository.deleteById(id);
		return element;
	}

	@Override
	public List<LevelStudy> getLevelsGeneral() {
		return repository.findAll();
	}

	@Override
	public Page<LevelStudy> getLevelStudyAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<LevelStudy> response = repository.getAllLevels(pageable);
		return response;	
	}
	
	
	private LevelStudy existLevel(Long id) throws GenericException {
		LevelStudy element = findLevelStudyById(id);
		
		if(element == null) {
			throw new GenericException("No hemos encontrado el recurso");
		}
		
		return element;
	}

}
