package com.services.chambitas.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Complaints;
import com.services.chambitas.domain.dto.ComplaintDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IComplaintService {
	
	  Page<Complaints> getAllComplaintsByAdmin(int pageNo, int pageSize);
	  
	  Page<Complaints> getAllComplaintsOfferByUserRH(Long userId,int pageNo, int pageSize);
	  
	  Page<Complaints> getAllComplaintsOfferByOffer(String keyword, Long offerId,int pageNo, int pageSize);
	  	  
	  Complaints createComplaint(ComplaintDTO request) throws GenericException;
	  
	  Complaints deleteComplaint(Long id) throws GenericException;
	 
}

