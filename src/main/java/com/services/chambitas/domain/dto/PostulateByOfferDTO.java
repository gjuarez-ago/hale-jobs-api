package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class PostulateByOfferDTO {

	private Long offerId;

	private Long userId;
	
	private String comments;
			
	private int status;
	
		
}
