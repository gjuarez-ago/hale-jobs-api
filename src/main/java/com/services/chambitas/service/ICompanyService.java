package com.services.chambitas.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Company;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;

@Service
public interface ICompanyService {
	
	Company findCompanyById(Long id) throws GenericException;
	
	Company deleteCompanyById(Long id) throws GenericException;
	
	String updateProfileImage(Long userId, Long id, MultipartFile image) throws IOException, NotAnImageFileException, GenericException;
	
	List<Company> getCompaniesByUser(Long user);
		
	Page<Company> getCompaniesGlobal(String keyword, int pageNo, int pageSize);
	
	Page<Company> getCompaniesByOwner(Long ownerId,String name, String rfc, String category, int pageNo, int pageSize);
		
	Company updateCompany(Long id, MultipartFile image, String name, String description, String category, String urlSite,
			String urlLinkedin, Long ownerId, String regimenFiscal, String rfc, String address, String numberPhone, String emailContact, String sizeCompany,  boolean showCompany, boolean updateImagen) throws GenericException, NotAnImageFileException, IOException;
	
	Company createCompany(MultipartFile image,String name,String description,String category,String urlSite,String urlLinkedin,Long ownerId,String regimenFiscal,String rfc,String address,String numberPhone, String emailContact, String sizeCompany, boolean isPublic) throws NotAnImageFileException, IOException, GenericException;
	
}
