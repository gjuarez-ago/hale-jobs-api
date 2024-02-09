package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	
private String names;
	
    private String motherln;
    
    private String fatherln;
   
	private String gender;
	
	private String password;
	
	private String email;
	
	private Date dateOfBirth;
	
	private String numberphone;

}
