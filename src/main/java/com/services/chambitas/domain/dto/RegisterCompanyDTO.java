package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RegisterCompanyDTO {
	
	private int typeUser;
	
	private String names;
	
	private String motherLastName;
	
	private String fatherLastName;
	
	private String username;
	
	private String numberPhone;
	
	private String password;
	
	private String gender;
	
	private Date dateOfBirth;
	
	
	private Long city;
	
	private Long state;
	
	private String title;
	
	private Long category;
	
	private Long subcategory;
	
	private String RFC;
	

}
