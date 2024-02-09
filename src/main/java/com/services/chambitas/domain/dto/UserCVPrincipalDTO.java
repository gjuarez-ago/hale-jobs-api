package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserCVPrincipalDTO {

	private String username;
	
	private String name;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private String email;

    private String state;
	
	private Long city;

    private String gender;
	
	private Date dateOfBirth;
	
	private String numberPhone;
	
	private Long modalidadTrabajo;
			
	private String relocated;
		

}
