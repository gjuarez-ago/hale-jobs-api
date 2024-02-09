package com.services.chambitas.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Complaints;
import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.ComplaintDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IComplaintRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IComplaintService;


@Service
@Transactional
public class ComplaintServiceImpl implements IComplaintService{

	@Autowired
	private IComplaintRepository repository;
	
	@Autowired
	private IOfferRepository offerRepository;
	
	@Autowired
	private IUserRepository userRepository;
	

	@Override
	public Page<Complaints> getAllComplaintsByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Complaints> response = repository.findAll(pageable);
		return response;
	}

	@Override
	public Page<Complaints> getAllComplaintsOfferByUserRH(Long userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Complaints> response = repository.findAll(pageable);
		return response;
	}

	@Override
	public Complaints createComplaint(ComplaintDTO request) throws GenericException {
		
		Offer offer = exisOffer(request.getOffer());
		User user = existUser(request.getUser());
		existComplaint(request.getUser(), offer.getId());
		
		Complaints element = new Complaints();
		element.setComments(request.getComments());
		element.setOffer(offer);
		element.setRegCreatedBy(request.getUser());
		element.setRegDateCreated(new Date());
		element.setStatus(request.getStatus());
		element.setTitle(request.getTitle());
		element.setUser(user);
	
		repository.save(element);
		
		
		return element;
	}

	@Override
	public Complaints deleteComplaint(Long id) throws GenericException {
       Complaints response =existComplaint(id);
       repository.deleteById(id);
		return response;
	}
	
	@Override
	public Page<Complaints> getAllComplaintsOfferByOffer(String keyword, Long offerId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Complaints> response = repository.findComplaintsByOffer(keyword, offerId, pageable);
		return response;
	}
	
	
	private Complaints existComplaint(Long id) throws GenericException {
		Complaints element = repository.findComplaintsById(id);
		if(element == null) {throw new GenericException("No se encontro la queja");}
		return element;
	}
	
	

	private Complaints existComplaint(Long userId, Long offerId) throws GenericException {
	     
		Complaints postulate = repository.findComplaintsByUserAndOffer(userId, offerId);
		
		if(postulate != null) {
			throw new GenericException("Ya has hecho una queja a esta vacante.");
		}
		
		return postulate;
	}
	
	
	private User existUser(Long id) throws GenericException {
		User user = userRepository.findUserById(id);
		if(user == null) {throw new GenericException("No se encontro el usuario");}
		return user;
	}
	
	private Offer exisOffer(String consecutive) throws GenericException {
		Offer offer = offerRepository.findOfferByConsecutive(consecutive);
		if(offer ==  null) {throw new GenericException("No se encontro la oferta");}
		return offer;
	}


}
