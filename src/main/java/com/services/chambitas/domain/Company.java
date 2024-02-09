/**
 * 
 */
package com.services.chambitas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String category;
	
	private double qualification;
	
	private String imageURL;	
	
	private String urlSite;
	
	private String urlLinkedin;
	
	private String sizeCompany;
	
	private Long ownerId;
	
	private String RFC;
	
	private String regimenFiscal;
		
	private boolean isvisible;
	
	private boolean isRecruiter;
	
	private String numberPhone;
	
	private String emailContact;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String address;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(columnDefinition = "integer default 0")	
	private int regBorrado;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateCreated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long regCreatedBy;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateUpdated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long regUpdateBy;
	
	
}
