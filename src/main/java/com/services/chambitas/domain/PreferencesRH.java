package com.services.chambitas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class PreferencesRH implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
    @Column(columnDefinition="LONGBLOB") 
    private String[] actitudesBlandas;
	
    @Column(columnDefinition="LONGBLOB") 
	private String[] actitudesTecnicas;
	
    @Column(columnDefinition="LONGBLOB") 
	private String[] areasSpecialidad;
    
    private Long userId;

	//Areas de especialidad []
	//Actitudes blandas que buscas en tus candidatos []
	//Actitudes tecnicas que buscas en tus candidatos []


	
}
