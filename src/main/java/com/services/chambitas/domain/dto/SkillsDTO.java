package com.services.chambitas.domain.dto;

import java.util.List;

import lombok.Data;

@Data
public class SkillsDTO {
	
	private Long userId;
	
	private List<String> skills;
	
}
