package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Skills;
import com.services.chambitas.domain.dto.SkillsDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ISkillsService {

	List<Skills> getSkillsGeneralOnlyKeywords();
	
	Page<Skills> getSkillsByAdmin(int pageNo, int pageSize);
	
	List<Skills> listByUser(Long user);
	
	Skills createByUser(Skills request);
	
	Skills updateById(Long id, Skills request) throws GenericException;
	
	List<Skills> updateSkillsByUser(SkillsDTO request);
	
	Skills deleteById(Long id) throws GenericException;
	
	Skills findById(Long id) throws GenericException;
	
}
