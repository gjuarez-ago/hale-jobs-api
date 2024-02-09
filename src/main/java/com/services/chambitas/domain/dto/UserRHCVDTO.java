package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserRHCVDTO {
	
    private String username;
	
	private String gender;
	
	private Date dateOfBirth;
	
	private String numberPhone;
	
	private String aboutMe;
	
	private String jobTitle;
		
	private String state;
	
	private Long city;
				
	private String[] actitudesBlandas;
	
	private String[] actitudesTecnicas;
	
	private String[] areasSpecialidad;
	

//Estado - 
//Ciudad -
//Genero -
//Número de telefonico -
//Fecha de nacimiento -
//Posición que ocupas actualmente -
//Areas de especialidad []
//Actitudes blandas que buscas en tus candidatos []
//Actitudes tecnicas que buscas en tus candidatos []
//Nos gustaria saber mas acerca de ti -


}
