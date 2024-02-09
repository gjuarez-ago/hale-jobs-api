/**
 * 
 */
package com.services.chambitas.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author gjuarezd
 *
*/

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
@Table(name = "cat_Usuarios")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    
    @Column(columnDefinition = "varchar(100)")
	private String names;

    @Column(columnDefinition = "varchar(100)")
	private String surnames;
	
    @Column(columnDefinition = "varchar(100)")
	private String motherLastName;
	
    @Column(columnDefinition = "varchar(100)")
	private String fatherLastName;
	
	@Column(nullable = false)	
	private String username;
	
	@Column(columnDefinition = "varchar(100)")
	private String email;
	
	@Column(columnDefinition = "varchar(30)")

	private String gender;
	
	private Date dateOfBirth;
	
	@Column(columnDefinition = "varchar(11)")
	private String numberPhone;

	@Column(columnDefinition = "varchar(50)")
	private String country;
	 
	//Puedes iniciar labores de inmediato
    private boolean publicProfile;
    
    private boolean profileCompleted;
    
    private String urlCV;
    
	@Column(columnDefinition = "TEXT")
    private String aboutMe;
    
    private String jobTitle;

	// La compañia de la persona
	@ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private CityINEGI city;
	
	// La compañia de la persona
	@ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private StateINEGI state;
	
	@ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private RangeAmount salary;
	
	@ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private TypeOfJob modalidadTrabajo;
	
    private Long companyId;
    
	@Column(columnDefinition = "varchar(5)")
    private String relocated;
    
    public String findJob;
    
    public boolean becomeRapidly;
    
    // 1: user 2: recruiter
    private int typeOfUser;
	     
	private String profileImageUrl;
	
    //-----------------------------------------//
    private boolean isActive;
	
	private boolean isNotLocked;
	
    private Date lastLoginDate;
    
    private Date lastLoginDateDisplay;
    
	private Date joinDate;
   
    private String role;
    
    @ManyToMany(targetEntity = Permission.class,cascade = CascadeType.ALL)
 	private List<Permission> permissions;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
    
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  	private String token;
  	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  	private Date expireToken;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String[] authorities;
    
    @JsonProperty(access = Access.WRITE_ONLY)
	@Column(columnDefinition = "integer default 0")
	private int regBorrado;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateCreated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regCreatedBy;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateUpdated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regUpdateBy;


}
