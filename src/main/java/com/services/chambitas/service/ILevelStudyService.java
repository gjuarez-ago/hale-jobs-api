package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.LevelStudy;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ILevelStudyService {
	
	LevelStudy findLevelStudyById(Long id) throws GenericException;
	
	LevelStudy createLevelStudy(GenericDTO request);
	
	LevelStudy updateLevelStudy(Long id, GenericDTO request) throws GenericException;
	
	LevelStudy deleteLevelStudyById(Long id) throws GenericException;
	
	List<LevelStudy> getLevelsGeneral();
	
	Page<LevelStudy> getLevelStudyAdmin(int pageNo, int pageSize);

}
