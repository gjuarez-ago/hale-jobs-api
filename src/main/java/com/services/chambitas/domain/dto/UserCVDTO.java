package com.services.chambitas.domain.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserCVDTO {
	
	private String username;
	
	private String gender;
	
	private Date dateOfBirth;
	
	private String numberPhone;
	
	private String aboutMe;
	
	private String jobTitle;
	
	private Long modalidadTrabajo;
		
	private String state;
	
	private Long city;
	
	private Long salary;

	private List<String> skills;
	
	private List<SchoolDTO> schools;
	
	private List<WorkExperiencesDTO> experiences;
	
	// Others
	
	private String[] findJobs;
	
	private String[] learnSkills;
	
	private String findJob;
		
	private String relocated;
	

}
