package com.services.chambitas.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.CommentsOffer;
import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.CommentsOfferDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICommentsOfferRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.ICommentsByOfferService;

@Service
@Transactional
public class CommentsOfferServiceImpl implements ICommentsByOfferService{

	@Autowired
	private ICommentsOfferRepository commentsOfferRepository;	
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IOfferRepository offerRepository;
	
	@Override
	public CommentsOffer createComment(CommentsOfferDTO request) throws GenericException {
		
	    CommentsOffer element = new CommentsOffer();
		
		User user =  existUser(request.getUserId());
		Offer offer = exisOffer(request.getOfferId());
		
		element.setConsecutive(generateConsecutive());
		element.setComment(request.getComment());
		element.setOffer(offer);
		element.setUser(user);
		
		element.setRegDateCreated(new Date());
		element.setRegCreatedBy(user.getId());
		
		commentsOfferRepository.save(element);
		return element;
	}

	@Override
	public CommentsOffer editCommentById(String id, CommentsOfferDTO request) throws GenericException {
		
		User user =  existUser(request.getUserId());
		CommentsOffer element =  existCommentsOffer(id);
		
		element.setComment(request.getComment());
		element.setRegDateUpdated(new Date());
		element.setRegUpdateBy(user.getId());
		
		commentsOfferRepository.save(element);
		
		return element;
	}

	@Override
	public CommentsOffer findCommentById(String id) throws GenericException {
		CommentsOffer element =  existCommentsOffer(id);
		return element;
	}

	@Override
	public CommentsOffer deleteCommentById(String id) throws GenericException {
		CommentsOffer element =  existCommentsOffer(id);
		commentsOfferRepository.deleteById(element.getId());
		return element;
	}

	@Override
	public List<CommentsOffer> getAllCommentsByOfferMovil(String id) {
		List<CommentsOffer>  list = commentsOfferRepository.findCommentsByOfferMovil(id);
		return list;
	}

	@Override
	public Page<CommentsOffer> getAllCommentsByOfferWEB(String id, int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<CommentsOffer> response = commentsOfferRepository.findCommentsByOfferWEB(id, pageable);
		return response;	
	}

	@Override
	public Page<CommentsOffer> getAllCommentsWebAdmin(String keyword, int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<CommentsOffer> response = commentsOfferRepository.findCommentsByOfferAdmin(keyword, pageable);
		return response;
	}
	
	// Métodos privados
	
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
	
	private CommentsOffer existCommentsOffer(String consecutive) throws GenericException {
		
		CommentsOffer commentsOffer = commentsOfferRepository.findCommentsByIdMovil(consecutive);
		
		if(commentsOffer == null) {
			throw new GenericException("No se encontro el comentario");
		}
		
		return commentsOffer;
	}
	
  
	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = commentsOfferRepository.count();
	    lastElement += 1;
	    
	    if(lastElement >= 0 && lastElement < 10) {consecutive = "100000000"  + lastElement;}
		if(lastElement >= 10 && lastElement < 100) {consecutive = "10000000"  + lastElement;}
		if(lastElement >= 100 && lastElement < 1000) {consecutive = "1000000"  + lastElement;}
		if(lastElement >= 1000 && lastElement < 10000) {consecutive = "100000"  + lastElement;}
		if(lastElement >= 10000 && lastElement < 100000) {consecutive = "10000"  + lastElement;}
		if(lastElement >= 100000 && lastElement < 1000000) {consecutive = "1000"  + lastElement;}
		if(lastElement >= 1000000 && lastElement < 10000000) {consecutive = "100"  + lastElement;}
		if(lastElement >= 10000000 && lastElement < 100000000) {consecutive = "10"  + lastElement;}
		if(lastElement >= 100000000 && lastElement < 1000000000) {consecutive = "1"  + lastElement;}
		return consecutive;
	}

}
