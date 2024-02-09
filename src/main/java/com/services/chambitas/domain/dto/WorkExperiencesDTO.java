package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class WorkExperiencesDTO {
	
	private String job;
	
	private String company;
	
	private String description;
	
	private String begins;
	
	private String ends;
	
	private String[] skills;
	
	private boolean worked;
	

}
