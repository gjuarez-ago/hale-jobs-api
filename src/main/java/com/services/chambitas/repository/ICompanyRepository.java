package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Company;

public interface ICompanyRepository extends JpaRepository<Company, Long>{
	
	Company findCompanyById(Long id);
	
	Company findCompanyByNameIgnoreCase(String name);
	 
	Company findCompanyByRFC(String rfc);
	 
    @Query(value = "SELECT c FROM Company c WHERE c.name LIKE %:name% AND c.regBorrado = 0")
	Page<Company> getCompaniesGlobal(@Param(value = "name") String name, Pageable pageable);
    
    @Query(value = "SELECT c FROM Company c WHERE c.ownerId = :ownerId AND c.regBorrado = 0")
	List<Company> getCompaniesByUser(@Param(value = "ownerId") Long ownerId);
    
    @Query(value = "SELECT c FROM Company c WHERE c.regBorrado = 0")
	List<Company> getCompanies();
    
    @Query(value = "SELECT c FROM Company c WHERE owner_id = :ownerId AND c.name LIKE %:name% AND c.RFC LIKE %:rfc% AND c.category LIKE %:category%  AND c.regBorrado = 0")
    Page<Company> getCompaniesByOwnerId(@Param(value = "ownerId") Long ownerId, @Param(value = "name") String name, @Param(value = "rfc") String rfc, @Param(value = "category") String category,   Pageable pageable );
    
    
    
}
