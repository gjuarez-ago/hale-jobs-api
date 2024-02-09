package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class RegisterDTO {
	
	private String names;
	
	private String motherLastName;
	
	private String fatherLastName;
	
	private String username;
	
	private String password;
	
	private int typeOfUser;
	
}
