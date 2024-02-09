package com.services.chambitas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class WorkExperiences implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	private String job;
	
	private String company;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	private String begins;
	
	private String ends;
	
	private boolean currentlyWork;
	
    @Column(columnDefinition="LONGBLOB") 
	private String[] skills;
	
	private Long userId;
	
	private boolean worked;
	
}
