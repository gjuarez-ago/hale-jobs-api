package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.RangeAmount;
import com.services.chambitas.domain.dto.GenericDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IRangeAmountService {
	
	RangeAmount createRangeAmount(GenericDTO request);
	
	RangeAmount editRangeAmount(Long id, GenericDTO request) throws GenericException;
	
	RangeAmount deleteRangeAmount(Long id) throws GenericException;
	
	RangeAmount findRangeAmount(Long id) throws GenericException;
	
	List<RangeAmount> getAllRangeAmount();
	
	Page<RangeAmount> getAllRangeAmountAdmin(int pageNo, int pageSize);
	

}
