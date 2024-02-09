package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.CommentsOffer;
import com.services.chambitas.domain.dto.CommentsOfferDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.ICommentsByOfferService;

@RestController
@RequestMapping(path = {"/comments-by-offer"})
public class CommentsByOfferController {

	@Autowired 
	ICommentsByOfferService service;

	@PostMapping("/create")
	public ResponseEntity<CommentsOffer> createComment(@RequestBody CommentsOfferDTO request) throws GenericException {
		CommentsOffer response = service.createComment(request);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PostMapping("/edit{key}")
	public ResponseEntity<CommentsOffer> editComment(@PathVariable("key") String id,@RequestBody CommentsOfferDTO request) throws GenericException {
		CommentsOffer response = service.createComment(request);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find/{key}")
	public ResponseEntity<CommentsOffer> findComment(@PathVariable("key") String id) throws GenericException {
		CommentsOffer response = service.findCommentById(id);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@DeleteMapping("/find/{key}")
	public ResponseEntity<CommentsOffer> deleteComment(@PathVariable("key") String id) throws GenericException {
		CommentsOffer response = service.deleteCommentById(id);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find-by-offer-m/{key}")
	public ResponseEntity<List<CommentsOffer>> getAllCommentsMovil(@PathVariable("key") String offerId) {
		List<CommentsOffer> response = service.getAllCommentsByOfferMovil(offerId);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find-by-offer-w/{offerId}")
	public ResponseEntity<Page<CommentsOffer>> getAllCommentsWeb(@RequestParam("offerId") String offerId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<CommentsOffer> response = service.getAllCommentsByOfferWEB(offerId, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/view-admin")
	public ResponseEntity<Page<CommentsOffer>> getAllCommentsAdmin(@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

		Page<CommentsOffer> response = service.getAllCommentsWebAdmin(keyword, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	

}
