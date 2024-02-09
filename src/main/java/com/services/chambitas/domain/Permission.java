package com.services.chambitas.domain;

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
public class Permission {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   @Column(nullable = false, updatable = false)
	   private Long id;
	   
	   @Column(nullable = false)
	   String keyPermission;
	   
	   @Column(nullable = false)
	   String description;
	 
}