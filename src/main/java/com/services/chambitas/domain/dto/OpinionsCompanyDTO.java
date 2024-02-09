package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class OpinionsCompanyDTO {

	private String title;
	
	private String opinion;
	
	private double rangeAmountQ;
	
	private double culture;
	
	private double oportunities;
	
	private Long company;
	
	private Long userId;
	
	private double rating;
	
	
}
