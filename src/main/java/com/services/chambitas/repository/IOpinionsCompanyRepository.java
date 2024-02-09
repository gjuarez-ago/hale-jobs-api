package com.services.chambitas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.OpinionsCompany;

public interface IOpinionsCompanyRepository extends JpaRepository<OpinionsCompany, Long> {
	
	OpinionsCompany findOpinionsCompanyById(Long id);
	
	@Query(value = "SELECT * from opinions_company AS oc WHERE oc.company_id = :company",nativeQuery = true)
    Page<OpinionsCompany> getAllOpinionsByFilters(@Param("company") Long company, Pageable pageable);
	
	@Query(value = "SELECT * from opinions_company AS oc WHERE oc.company_id = :company AND oc.user_id = :user",nativeQuery = true)
    OpinionsCompany findOpinionByUserAndCompany(@Param("company") Long company, @Param("user") Long userId);


}
