package com.services.chambitas.service.impl;

import static com.services.chambitas.constant.FileConstant.DIRECTORY_CREATED;
import static com.services.chambitas.constant.FileConstant.DOT;
import static com.services.chambitas.constant.FileConstant.FILE_SAVED_IN_FILE_SYSTEM;
import static com.services.chambitas.constant.FileConstant.IMAGES_FOLDER;
import static com.services.chambitas.constant.FileConstant.JPG_EXTENSION;
import static com.services.chambitas.constant.FileConstant.PRODUCT_IMAGE_PATH;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.services.chambitas.domain.Company;
import com.services.chambitas.domain.File;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.repository.ICompanyRepository;
import com.services.chambitas.repository.IFileRepository;
import com.services.chambitas.service.ICompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements ICompanyService{
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	
	@Autowired
	private ICompanyRepository repository;
	
	@Autowired
	private IFileRepository fileRepository;
	
//	@Autowired 
//	private IJobCategoryRepository categoryRepository;
	
		
	@Override
	public Company findCompanyById(Long id) throws GenericException {
		return existCompany(id);
	}

	@Override
	public Company deleteCompanyById(Long id) throws GenericException {
		Company response = existCompany(id);
		
		response.setRegBorrado(1);
		repository.save(response);
		
		return response;
	}

	@Override
	public String updateProfileImage(Long userId, Long id, MultipartFile image) throws IOException, NotAnImageFileException, GenericException {
		
		Company company =  existCompany(id);
		File entityFile = new File();
		
		if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(image.getContentType())) {
            throw new NotAnImageFileException(image.getOriginalFilename() + "is not an image file. Please upload an image file");
		}
		
		saveImage(userId, entityFile, image);	
		company.setImageURL(entityFile.getRouteFile());
		
		return "Se actualizo correctamente la imagen!";
	}

	
	@Override
	public Page<Company> getCompaniesGlobal(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Company> response = repository.getCompaniesGlobal(keyword, pageable);
		return response;	
	}
	
	@Override
	public Company updateCompany(Long id, MultipartFile image, String name, String description, String category, String urlSite,
			String urlLinkedin, Long ownerId, String regimenFiscal, String rfc, String address, String numberPhone, String emailContact, String sizeCompany,  boolean showCompany, boolean updateImagen) throws GenericException, NotAnImageFileException, IOException {
		
	    Company company = existCompany(id);
	    validateRFCUpdated(rfc, company.getRFC());
	    validateNameUpdated(name, company.getName());
	    
		if(updateImagen) {
			
	        File entityFile = new File();
	        
			if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(image.getContentType())) {
	            throw new NotAnImageFileException(image.getOriginalFilename() + "is not an image file. Please upload an image file");
			}
			
			saveImage(ownerId, entityFile, image);
		    company.setImageURL(entityFile.getRouteFile());

		}
		
		company.setEmailContact(emailContact);
		company.setNumberPhone(numberPhone);
		company.setAddress(address);
		company.setCategory(category);
		company.setDescription(description);
	    company.setIsvisible(showCompany);
	    company.setName(name);
	    company.setOwnerId(ownerId);
	    company.setQualification(0);
	    company.setRecruiter(false);
	    company.setRegimenFiscal(regimenFiscal);
	    company.setRFC(rfc);
	    company.setSizeCompany(sizeCompany);	    
	    company.setUrlLinkedin(urlLinkedin);
	    company.setUrlSite(urlSite);
	   
	    company.setRegDateUpdated(new Date());
	    company.setRegUpdateBy(ownerId);
	    
		return company;
	}

	@Override
	public List<Company> getCompaniesByUser(Long id) {
		return repository.getCompaniesByUser(id);
	}	
	
	@Override
	public Page<Company> getCompaniesByOwner(Long ownerId,  String name, String rfc, String category, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Company> response = repository.getCompaniesByOwnerId(ownerId, name, rfc, category,pageable);
		return response;
	}
	
	
	
	
	
	private String setProfileImageUrl(String uuid) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(PRODUCT_IMAGE_PATH + uuid + "/"
        + uuid + DOT + JPG_EXTENSION).toUriString();
    }

	@Override
	public Company createCompany(MultipartFile image, String name, String description, String category, String urlSite,
			String urlLinkedin, Long ownerId, String regimenFiscal, String rfc, String address, String numberPhone, String emailContact, String sizeCompany,  boolean showCompany) throws NotAnImageFileException, IOException, GenericException {
	
		validateName(name);
		validateRFC(rfc);
		Company company =  new Company();
		File entityFile = new File();

		
		if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(image.getContentType())) {
            throw new NotAnImageFileException(image.getOriginalFilename() + "is not an image file. Please upload an image file");
		}
		
		saveImage(ownerId, entityFile, image);	
		
		company.setEmailContact(emailContact);
		company.setNumberPhone(numberPhone);
		company.setAddress(address);
		company.setCategory(category);
		company.setDescription(description);
	    company.setIsvisible(showCompany);
	    company.setName(name);
	    company.setOwnerId(ownerId);
	    company.setQualification(0);
	    company.setRecruiter(false);
	    company.setRegimenFiscal(regimenFiscal);
	    company.setRFC(rfc);
	    company.setSizeCompany(sizeCompany);
	    
	    company.setImageURL(entityFile.getRouteFile());
	    company.setUrlLinkedin(urlLinkedin);
	    company.setUrlSite(urlSite);
	    
	    company.setRegDateCreated(new Date());
	    company.setRegCreatedBy(ownerId);
	    company.setRegBorrado(0);
	    
	    repository.save(company);
		
		return company;
	}
	
	private File saveImage(Long id, File entity,MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
           
       	final String uuid = UUID.randomUUID().toString().toLowerCase();
       	  
       	Path userFolder = Paths.get(IMAGES_FOLDER + uuid).toAbsolutePath().normalize();
       	  
           if(!Files.exists(userFolder)) {
                 Files.createDirectories(userFolder);
                 LOGGER.info(DIRECTORY_CREATED + userFolder);
           }
             
           Files.deleteIfExists(Paths.get(userFolder + uuid + DOT + JPG_EXTENSION));
           Files.copy(profileImage.getInputStream(), userFolder.resolve(uuid + DOT + JPG_EXTENSION), REPLACE_EXISTING);
           entity.setConsecutive(generateConsecutive());
           entity.setRouteFile(setProfileImageUrl(uuid));
           entity.setNameFile(uuid);
           entity.setNameEntity("COMPANY_" + uuid);
           entity.setRegCreatedBy(id);
           entity.setRegDateCreated(new Date());
           fileRepository.save(entity);
           LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
       }
		return entity;
    }
	
	
//	private JobCategory existCategory(Long id) throws GenericException {
//		
//		JobCategory category = categoryRepository.findJobCategoryById(id);
//	
//		if(category == null) {
//			throw new GenericException("No se encontro la categoría");
//		}
//		
//		return category;
//	}
	
	private Company existCompany(Long id) throws GenericException {
		
		Company element = repository.findCompanyById(id);
		
		if(element == null) {
			throw new GenericException("No hemos encontrado la compañia");
		}
		
		return element;
	}
	
	
	private Company validateRFC(String rfc) throws GenericException {
		
		Company element = repository.findCompanyByRFC(rfc);
		
		if(element != null) {
			throw new GenericException("Ya existe una empresa con el mismo RFC");
		}
		
		return element;
	}
	
	
	private Company validateRFCUpdated(String rfc, String currentRFC) throws GenericException {
		
		Company element = repository.findCompanyByRFC(rfc);
		
		if(element != null) {
				throw new GenericException("Ya existe una empresa con el mismo RFC");
		}
		
		return element;
	}
	
	private Company validateName(String name) throws GenericException {
		
        Company element = repository.findCompanyByNameIgnoreCase(name);
        
		if(element != null) {
			throw new GenericException("Ya existe una empresa con este nombre!");
		}
		
		return element;
	}
	
	private Company validateNameUpdated(String name, String currentName) throws GenericException {
		
        Company element = repository.findCompanyByNameIgnoreCase(name);
        
		if(element != null && element.getRFC() != name) {
				throw new GenericException("Ya existe una empresa con este nombre!");
		}
		
		return element;
	}
	
	
	
	
	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = fileRepository.count();
	    lastElement += 1;
	    
	    if(lastElement >= 0 && lastElement < 10) {consecutive = "100000000"  + lastElement;}
		if(lastElement >= 10 && lastElement < 100) {consecutive = "10000000"  + lastElement;}
		if(lastElement >= 100 && lastElement < 1000) {consecutive = "1000000"  + lastElement;}
		if(lastElement >= 1000 && lastElement < 10000) {consecutive = "100000"  + lastElement;}
		if(lastElement >= 10000 && lastElement < 100000) {consecutive = "10000"  + lastElement;}
		if(lastElement >= 100000 && lastElement < 1000000) {consecutive = "1000"  + lastElement;}
		if(lastElement >= 1000000 && lastElement < 10000000) {consecutive = "100"  + lastElement;}
		if(lastElement >= 10000000 && lastElement < 100000000) {consecutive = "10"  + lastElement;}
		if(lastElement >= 100000000 && lastElement < 1000000000) {consecutive = "1"  + lastElement;}
		return consecutive;
	}

	
	

}
