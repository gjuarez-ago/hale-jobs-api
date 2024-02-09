package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.CommentsOffer;
import com.services.chambitas.domain.dto.CommentsOfferDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ICommentsByOfferService {
	
	CommentsOffer createComment(CommentsOfferDTO request) throws GenericException;
	
	CommentsOffer editCommentById(String id, CommentsOfferDTO request) throws GenericException;
	
	CommentsOffer findCommentById(String id) throws GenericException;
	
	CommentsOffer deleteCommentById(String id) throws GenericException;
	
	List<CommentsOffer> getAllCommentsByOfferMovil(String id);
	
	Page<CommentsOffer> getAllCommentsByOfferWEB(String id, int pageNo, int pageSize);
	
	Page<CommentsOffer> getAllCommentsWebAdmin(String keyword, int pageNo, int pageSize);	

}
