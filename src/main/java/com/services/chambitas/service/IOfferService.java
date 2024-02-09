package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.dto.OfferDTO;
import com.services.chambitas.domain.dto.OfferEditDTO;
import com.services.chambitas.domain.response.ChartsDashboardResponse;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IOfferService {
	
	Offer createOffer(OfferDTO request) throws GenericException;
	
	Offer editOffer(OfferEditDTO request) throws GenericException;	
	
	Offer deleteOfferById(Long id, Long userId) throws GenericException;
		
	Offer findOfferById(Long id) throws GenericException;	
	
	Offer reportOffer(Long id, String comment, String category, Long userId) throws GenericException;
		
	List<Offer> getAllOfferByUserMovil(Long userId, String title, String subcategory, String rangeAmount, String urgency, String workPlace,String levelStudy, String typeJob,int status);
	
	Page<Offer> getAllOfferByUserWEB(Long userId, String title, String subcategory, String rangeAmount, String urgency, String workPlace,String levelStudy, String typeJob,int status, int pageNo, int pageSize);
	
	List<Offer> findOfferGeneralMovil(String keyword);

	Page<Offer> findOfferGeneralWEB(String title, Long category,String subcategory,String rangeAmount, String state, String typeJob, String urgency, int pageNo, int pageSize);
	
	Page<Offer> getAllOfferByAdmin(String keyword,int pageNo, int pageSize);
	
	Page<Offer> getAllOfferByCompany(Long company,int pageNo, int pageSize);
	
	Page<Offer> getOffersByCopy(String keyword, Long user, int pageNo, int pageSize);

	List<Offer> getOffersBySelect(Long userId);
	
	List<ChartsDashboardResponse> getDashboard();
	

}
