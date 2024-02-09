package com.services.chambitas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "tbl_files")
public class File  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
		
	@Column(nullable = false)
	private String consecutive;
	
	@Column(nullable = false)
	private String routeFile;
	
	@Column(nullable = false)
	private String nameEntity;
		
	@Column(nullable = false)
	private String nameFile;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateCreated;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long regCreatedBy;
	
	
}
