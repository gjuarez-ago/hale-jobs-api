package com.services.chambitas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class OpinionsCompany implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	private String title;
	
	private String opinion;
	
	private double rangeAmountQ;
	
	private double culture;
	
	private double oportunities;
	
	private Long userId;
	
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Company company;
	
	private Date regDateCreated;
 
	
	
}
