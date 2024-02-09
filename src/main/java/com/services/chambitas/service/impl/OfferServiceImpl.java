package com.services.chambitas.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.CityINEGI;
import com.services.chambitas.domain.Company;
import com.services.chambitas.domain.Complaints;
import com.services.chambitas.domain.Countries;
import com.services.chambitas.domain.JobCategory;
import com.services.chambitas.domain.JobSubcategory;
import com.services.chambitas.domain.LevelStudy;
import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.RangeAmount;
import com.services.chambitas.domain.StateINEGI;
import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.TypeOfPayment;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.OfferDTO;
import com.services.chambitas.domain.dto.OfferEditDTO;
import com.services.chambitas.domain.response.ChartsDashboardResponse;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICityRepository;
import com.services.chambitas.repository.ICompanyRepository;
import com.services.chambitas.repository.IComplaintRepository;
import com.services.chambitas.repository.ICountriesRepository;
import com.services.chambitas.repository.IJobCategoryRepository;
import com.services.chambitas.repository.IJobSubcategoryRepository;
import com.services.chambitas.repository.ILevelStudyRepository;
import com.services.chambitas.repository.INotificationRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IRangeAmountRepository;
import com.services.chambitas.repository.IStateRepository;
import com.services.chambitas.repository.ITypeOfJobRepository;
import com.services.chambitas.repository.ITypeOfPaymentRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IOfferService;


@Service
@Transactional
public class OfferServiceImpl implements IOfferService{
	
	@Autowired
	private IOfferRepository offerRepository;
	
	@Autowired
	private IUserRepository userRepository;
		
	@Autowired 
	private ITypeOfPaymentRepository typeOfPaymentRepository;
	
	@Autowired
	private ITypeOfJobRepository typeOfJobRepository;
	
	@Autowired
	private IComplaintRepository complaintRepository;
	
	@Autowired
	private IJobCategoryRepository categoryRepository;
	
	@Autowired
	private ILevelStudyRepository levelRepository;
	
	@Autowired 
	private ICountriesRepository countryRepository;
	
	@Autowired
	private ICityRepository cityRepository;
	
	@Autowired
	private IStateRepository stateRepository;
	
	@Autowired
	private ICompanyRepository companyRepository;
	
	@Autowired 
	private IJobSubcategoryRepository subCategoryRepository;
	
	@Autowired
	private IRangeAmountRepository rangeAmountRepository;
	
	@Autowired
	private INotificationRepository notificationRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public Offer createOffer(OfferDTO request) throws GenericException {
		
		Offer element = new Offer();
		
		// Entidades
		TypeOfPayment tpyePayment = existTypePayment(request.getTypeOfPayment());
		TypeOfJob typeJob = existTypeJob(request.getTypeOfJob());
		User user = existUser(request.getUserId());
		JobCategory category = existCategory(request.getCategory());
		CityINEGI city = existCity(request.getCity());
		StateINEGI state = existState(request.getState());
		Countries country = existCountry(request.getCountry());
		Company company = existCompany(request.getCompany());
		LevelStudy study = existLevelStudy(request.getLevelStudy());
		RangeAmount rangeAmount = existRangeAmount(request.getRangeAmount());
		JobSubcategory subcategory = existSubcategory(request.getSubcategory());
		
		 Date dt = new Date();
		 Calendar c = Calendar.getInstance();
		 c.setTime(dt);
		 c.add(Calendar.DATE, 7);
		 dt = c.getTime();
		
        element.setConsecutive(generateConsecutive());
        element.setTitle(request.getTitle());
        element.setBenefits(request.getBenefits());
        element.setCategory(category);
        element.setCity(city);
        element.setComment(request.getAddress());
        element.setCompany(company);
        element.setCountry(country);
        element.setDescription(request.getDescription());
        element.setHaveComplaint(false);
        element.setLevelStudy(study);
        element.setMainActivities(request.getMainActivities());
        element.setRangeAmount(rangeAmount);
        element.setShowCompany(request.isShowCompany());
        element.setShowSalary(request.isShowSalary());
        element.setSkills(request.getSkills());
        element.setState(state);
        element.setStatus(0);
        element.setSubcategory(subcategory);
        element.setTypeOfPayment(tpyePayment);
        element.setTypeOfJob(typeJob);
        element.setUser(user);
        element.setTypeOfOffer(request.getTypeOfOffer());
        element.setWorkPlace(request.getWorkPlace());
        element.setUrgency(request.getUrgency());
        element.setVencimiento(dt);
        
		element.setRegDateCreated(new Date());
		element.setRegCreatedBy(request.getUserId());
		element.setRegBorrado(0);
		
		offerRepository.save(element);		
		
		return element;
	}

	@Override
	public Offer editOffer(OfferEditDTO request) throws GenericException {
	
		Offer element = exisOffer(request.getOfferId());
		
		// Entidades
		TypeOfPayment tpyePayment = existTypePayment(request.getTypeOfPayment());
		TypeOfJob typeJob = existTypeJob(request.getTypeOfJob());
		User user = existUser(request.getUserId());
		JobCategory category = existCategory(request.getCategory());
		Company company = existCompany(request.getCompany());
		LevelStudy study = existLevelStudy(request.getLevelStudy());
		RangeAmount rangeAmount = existRangeAmount(request.getRangeAmount());
		JobSubcategory subcategory = existSubcategory(request.getSubcategory());
		
		    element.setConsecutive(generateConsecutive());
	        element.setTitle(request.getTitle());
	        element.setBenefits(request.getBenefits());
	        element.setCategory(category);
	        element.setComment(request.getAddress());
	        element.setCompany(company);
	        element.setDescription(request.getDescription());
	        element.setHaveComplaint(false);
	        element.setLevelStudy(study);
	        element.setMainActivities(request.getMainActivities());
	        element.setRangeAmount(rangeAmount);
	        element.setShowCompany(request.isShowCompany());
	        element.setShowSalary(request.isShowSalary());
	        element.setSkills(request.getSkills());
	        element.setStatus(0);
	        element.setSubcategory(subcategory);
	        element.setTypeOfPayment(tpyePayment);
	        element.setTypeOfJob(typeJob);
	        element.setUser(user);
	        element.setTypeOfOffer(request.getTypeOfOffer());
	        element.setWorkPlace(request.getWorkPlace());
	        element.setUrgency(request.getUrgency());
	        
			element.setRegDateCreated(new Date());
			element.setRegCreatedBy(request.getUserId());
			element.setRegBorrado(0);
			
			offerRepository.save(element);		
			
		return element;
	}

	@Override
	public Offer deleteOfferById(Long id, Long userId) throws GenericException {
		
		Offer element = exisOffer(id);
		
		element.setRegBorrado(1);
		element.setRegDateUpdated(new Date());
		element.setRegUpdateBy(userId);
		
		offerRepository.save(element);
		
		return element;
	}

	@Override
	public Offer findOfferById(Long id) throws GenericException {
		Offer element = exisOffer(id);
		return element;
	}

	@Override
	public Offer reportOffer(Long id, String comment, String category, Long userId) throws GenericException {
		
		Offer offer = exisOffer(id);
		User user = existUser(userId);
		
		Complaints complaint = new Complaints();
		
		complaint.setComments(comment);
		complaint.setOffer(offer);
		complaint.setRegCreatedBy(user.getId());
		complaint.setUser(user);
		complaint.setRegDateCreated(new Date());
		complaint.setTitle(category);
		
		offer.setHaveComplaint(true); // La oferta ha sido reportada
		
		complaintRepository.save(complaint);
		offerRepository.save(offer);

		return offer;
	}

	@Override
	public List<Offer> getAllOfferByUserMovil(Long userId, String title, String subcategory, String rangeAmount, String urgency, String workPlace,String levelStudy, String typeJob, int status) {
	    List<Offer> list = offerRepository.findOfferByUserMovil(userId, title, subcategory, rangeAmount, urgency, workPlace , levelStudy,typeJob, status);
	    System.err.println(list.size());
		return list;
	}

	@Override
	public Page<Offer> getAllOfferByUserWEB(Long userId, String title, String subcategory, String rangeAmount, String urgency, String workPlace,String levelStudy, String typeJob, int status, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 	
		Page<Offer> response = offerRepository.findOfferByUserWEB(userId, title, subcategory, rangeAmount, urgency, workPlace , levelStudy,typeJob, status, pageable);		
		return response;	
	}

	@Override
	public List<Offer> findOfferGeneralMovil(String keyword) {
		return offerRepository.findOfferGeneralMovil(keyword);
	}



	@Override
	public Page<Offer> getAllOfferByAdmin(String keyword,int pageNo, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.findOfferAdmin(keyword, pageable);
		return response;
	}

	private TypeOfJob existTypeJob(Long clave) throws GenericException {
		TypeOfJob element = typeOfJobRepository.findTypeOfJobById(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	private TypeOfPayment existTypePayment(Long clave) throws GenericException {
		TypeOfPayment element = typeOfPaymentRepository.findTypeOfPaymentById(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	private User existUser(Long id) throws GenericException {
		User user = userRepository.findUserById(id);
		if(user == null) {throw new GenericException("No se encontro el usuario");}
		return user;
	}
	
	private Offer exisOffer(Long id) throws GenericException {
		Offer offer = offerRepository.findOfferById(id);
		if(offer ==  null) {throw new GenericException("No se encontro la oferta");}
		return offer;
	}
	
	
	private  JobCategory existCategory(Long id) throws GenericException {
	
		JobCategory response = categoryRepository.findJobCategoryById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado la categoría!");
		}
		
		return response;
	}
	
	
	private CityINEGI existCity(Long id) throws GenericException {
		CityINEGI response = cityRepository.findCityINEGIById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado la ciudad!");
		}
		
		return response;
	}
	
	
	private StateINEGI existState(String id) throws GenericException {
		StateINEGI response = stateRepository.findStateINEGIByClave(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado el estado!");
		}
		
		return response;
	}
	
	
	private Countries existCountry(Long id) throws GenericException {
		Countries response = countryRepository.findCountriesById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado el pais!");
		}
		
		return response;
	}
	
	private Company existCompany(Long id) throws GenericException {
		Company response =  companyRepository.findCompanyById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado la compañia!");
		}
		
		return response;
	}
	
	private LevelStudy existLevelStudy(Long id) throws GenericException {
		LevelStudy response = levelRepository.findLevelStudyById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado el nivel de estudio!");
		}
		
		return response;
	}
	
	
	private RangeAmount existRangeAmount(Long id) throws GenericException {
		RangeAmount response = rangeAmountRepository.findRangeAmountById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado el rango de sueldo!");
		}
		
		return response;
	}
	
	private JobSubcategory existSubcategory(Long id) throws GenericException {
		JobSubcategory response = subCategoryRepository.findJobSubcategoryById(id);
		
		if(response == null) {
			throw new GenericException("No hemos encontrado la subcategoría!");
		}
		
		return response;
	}
	
	
	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = offerRepository.count();
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

	@Override
	public Page<Offer> findOfferGeneralWEB(String title, Long category,String subcategory,String rangeAmount, String state, String typeJob, String urgency, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.findOfferGeneralWEB(title, category,subcategory,rangeAmount, state, typeJob, urgency, pageable);
		return response;
	}

	@Override
	public Page<Offer> getAllOfferByCompany(Long company, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.getOffersByCompany(company, pageable);
		return response;
	}

	@Override
	public Page<Offer> getOffersByCopy(String keyword, Long user, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.getOffersByCopy(keyword, user, pageable);
		return response;
	}

	@Override
	public List<Offer> getOffersBySelect(Long userId) {
		List<Offer> response = offerRepository.getOffersBySelect(userId);
		return response;
	}

	@Override
	public List<ChartsDashboardResponse> getDashboard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
