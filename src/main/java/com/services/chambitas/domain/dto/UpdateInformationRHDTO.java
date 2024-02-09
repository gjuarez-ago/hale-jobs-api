package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateInformationRHDTO {
	
	private Long id;
	
	private String emailContact;
	
	private String names;
	
	private String fatherLastName;
	
	private String motherLastName;
	
	private String jobTitle;
	
	private Date fechaNacimiento;
	
	private String gender;
	
	private String numberPhone;
	
	private String[] actitudesBlandas;
	
	private String[] actitudesTecnicas;
	
	private String[] areasSpecialidad;
    

}
