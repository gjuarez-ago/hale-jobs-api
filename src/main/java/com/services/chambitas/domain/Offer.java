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
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private String consecutive;
	
	// Titulo de la vacante
	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	// Urgencia del trabajo
	@Column(nullable = false)
	private String urgency;
	
	// Tiene un reporte
	private boolean haveComplaint;

	// Mostrar salario
	private boolean showSalary;
	
	// Mostrar datos de la compañia
	private boolean showCompany;
    
	// Beneficios de la vacante
	@Column(columnDefinition = "LONGBLOB")
	private String[] benefits;

	// Principales actividades
	@Column(columnDefinition = "LONGBLOB")
	private String[] mainActivities;
	
	// Principales habilidades
	@Column(columnDefinition = "LONGBLOB")
	private String[] skills;
	
	// Remoto, Presencial, Hibrido
	private String workPlace; 
	
	// Bronce - Plata - Oro
	private int typeOfOffer;
	
	// 1 : Creada, 2 : Pausada, 3 : Completada, 4 : Cerrada por usuario, 5 : Bloqueada por reportes
	@Column(nullable = false)
	private int status;
	
	// 7 días 
	@Column(nullable = false)
	private Date vencimiento;
	
	// La compañia de la persona
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Company company;

	// Tipo de trabajo -- Jornada completa, Practicas, Media jornada
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private TypeOfJob typeOfJob;

	// Honorarios, Mensual, Quincenal, Semanal
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private TypeOfPayment typeOfPayment;
	
	// Primer monto ofrecido - Rango 1
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private RangeAmount rangeAmount;
	
	// Usuario que creo la solicitud
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private User user;
	
	// Pais de donde se creo
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Countries country;
	
	// Estado del trabjo
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private StateINEGI state;

	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private LevelStudy levelStudy;
	
	// Ciudad de empleo
	@ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private CityINEGI city;
	
	// Categoria
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private JobCategory category;
		
	// subcategoria 
	@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private JobSubcategory subcategory;
	
	private Date regDateCreated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long regCreatedBy;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateUpdated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long regUpdateBy;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(columnDefinition = "integer default 0")
	private int regBorrado;

}
