package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.dto.TypeOfJobDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ITypeOfJobService {
	
	TypeOfJob createTypeOfJob(TypeOfJobDTO request);
	
	TypeOfJob editTypeOfJob(String id, TypeOfJobDTO request) throws GenericException;
	
	TypeOfJob deleteTypeOfJob(String id) throws GenericException;
	
	TypeOfJob findTypeOfJob(String id) throws GenericException;
	
	List<TypeOfJob> getAll();
	
	Page<TypeOfJob> getAllTypeOfJob(int pageNo, int pageSize);

}
