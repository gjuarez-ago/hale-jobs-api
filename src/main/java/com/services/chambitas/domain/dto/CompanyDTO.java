package com.services.chambitas.domain.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CompanyDTO {
	
	@Column(nullable = false)
	private String name;
	
	private String description;
	
	private String category;
	
	private String urlSite;
	
	private String urlLinkedin;
	
	private String sizeCompany;
	
	private Long ownerId;
	
	private String address;
	
	private boolean showCompany;
	
	private Long userId;
	
	private String numberPhone;
	
	private String emailContact;
	
}
