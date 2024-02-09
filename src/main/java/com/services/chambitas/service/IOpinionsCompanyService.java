package com.services.chambitas.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.OpinionsCompany;
import com.services.chambitas.domain.dto.OpinionsCompanyDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IOpinionsCompanyService {
	
	OpinionsCompany createOpinionsCompany(OpinionsCompanyDTO request) throws GenericException;
	
	OpinionsCompany updateOpinionsCompany(Long id, OpinionsCompanyDTO request) throws GenericException;	
	
	OpinionsCompany findOpinionById(Long id) throws GenericException;
	
	OpinionsCompany deleteOpinionById(Long id) throws GenericException;
	
	Page<OpinionsCompany> getOpinionsByCompay(Long companyId, String keyword, int pageNo, int pageSize);
	
}
