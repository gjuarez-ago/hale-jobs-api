package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class NotificationDTO {

	private String content;

	private String typeAD;
	
	private String emailDestination;
	
	private String sendBy;
	
	private String title;

	private Long userId;

	private Long offerId;
	
}
