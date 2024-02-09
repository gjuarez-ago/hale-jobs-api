package com.services.chambitas.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Company;
import com.services.chambitas.domain.OpinionsCompany;
import com.services.chambitas.domain.dto.OpinionsCompanyDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICompanyRepository;
import com.services.chambitas.repository.IOpinionsCompanyRepository;
import com.services.chambitas.service.IOpinionsCompanyService;

@Service
@Transactional
public class OpinionsCompanyServiceImpl implements IOpinionsCompanyService{

	
	@Autowired 
	private IOpinionsCompanyRepository repository;
	
	@Autowired 
	private ICompanyRepository companyRepository;
	
	@Override
	public OpinionsCompany createOpinionsCompany(OpinionsCompanyDTO request) throws GenericException {
		
		Company company = existCompany(request.getCompany());
		existOpinionByUser( request.getUserId(), request.getCompany());
		OpinionsCompany element = new OpinionsCompany();
		
		element.setCompany(company);
		element.setRangeAmountQ(request.getRangeAmountQ());
		element.setCulture(request.getCulture());
		element.setOpinion(request.getOpinion());
		element.setOportunities(request.getCulture());
		element.setTitle(request.getTitle());
		element.setUserId(request.getUserId());
		element.setRegDateCreated(new Date());
		company.setQualification(request.getRating());
		
		companyRepository.save(company);
		repository.save(element);
		
		return element;
	}

	@Override
	public OpinionsCompany updateOpinionsCompany(Long id, OpinionsCompanyDTO request) throws GenericException {
		
		Company company = existCompany(request.getCompany());
		OpinionsCompany element =  existOpinion(id);
		
		element.setCompany(company);
		element.setRangeAmountQ(request.getRangeAmountQ());
		element.setCulture(request.getCulture());
		element.setOpinion(request.getOpinion());
		element.setOportunities(request.getCulture());
		element.setTitle(request.getTitle());
		
		repository.save(element);
		
		return element;
	}

	@Override
	public OpinionsCompany findOpinionById(Long id) throws GenericException {
		return existOpinion(id);
	}

	@Override
	public OpinionsCompany deleteOpinionById(Long id) throws GenericException {
		OpinionsCompany element =  existOpinion(id);
		repository.deleteById(id);
		return element;
	}

	@Override
	public Page<OpinionsCompany> getOpinionsByCompay(Long companyId, String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<OpinionsCompany> response = repository.getAllOpinionsByFilters(companyId, pageable);
		return response;
	}

	private Company existCompany(Long id) throws GenericException {
		Company element = companyRepository.findCompanyById(id);
		if(element == null) {
			throw new GenericException("No se encontro la escuela");
		}
		return element;
	}

	private OpinionsCompany existOpinionByUser(Long userId, Long company) throws GenericException {
	     
		OpinionsCompany response = repository.findOpinionByUserAndCompany(company, userId);
		
		if(response != null) {
			throw new GenericException("Ya has hecho un comentario a esta empresa.");
		}
		
		return response;
	}
	
	private OpinionsCompany existOpinion(Long id) throws GenericException {
		OpinionsCompany element = repository.findOpinionsCompanyById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	
//	private float updateQualificationCompany() {
//		
//		return 0.0f;
//	}

}
