package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.RangeAmount;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IRangeAmountRepository;
import com.services.chambitas.service.IRangeAmountService;


@Service
@Transactional
public class RangeAmountServiceImpl implements IRangeAmountService{

	@Autowired
	private IRangeAmountRepository repository;
	
	@Override
	public RangeAmount createRangeAmount(GenericDTO request) {
		RangeAmount element = new RangeAmount();
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		repository.save(element);
		return element;
	}

	@Override
	public RangeAmount editRangeAmount(Long id, GenericDTO request) throws GenericException {

		RangeAmount element = existRangeAmount(id);
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		repository.save(element);
		return element;
	}

	@Override
	public RangeAmount deleteRangeAmount(Long id) throws GenericException {
		RangeAmount element = existRangeAmount(id);
        repository.deleteById(id);
		return element;
	}

	@Override
	public RangeAmount findRangeAmount(Long id) throws GenericException {
		return existRangeAmount(id);
	}

	@Override
	public List<RangeAmount> getAllRangeAmount() {
		return repository.findAll();
	}

	@Override
	public Page<RangeAmount> getAllRangeAmountAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<RangeAmount> response = repository.findAll(pageable);
		return response;
	}
		
	private RangeAmount existRangeAmount(Long id) throws GenericException {
		
		RangeAmount element = repository.findRangeAmountById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	
	

}
