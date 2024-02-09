package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class CommentsOfferDTO {
	
	private String consecutive;

	private String offerId;
	
	private Long userId;

	private String comment;


}
