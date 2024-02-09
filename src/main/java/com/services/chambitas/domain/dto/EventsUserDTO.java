package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EventsUserDTO {

	private Long userRecruiter;
	
	private Long userGuest;
	
	private Long offer;
	
	private int status;
	
	private Date dateBegin;
	
	private String title;
	
	private String description;
	
	private String urlMeet;
	
}
