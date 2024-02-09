package com.services.chambitas.service.impl;

import static com.services.chambitas.constant.FileConstant.DEFAULT_USER_IMAGE_PATH;
import static com.services.chambitas.constant.FileConstant.DOT;
import static com.services.chambitas.constant.FileConstant.FILE_SAVED_IN_FILE_SYSTEM;
import static com.services.chambitas.constant.FileConstant.FORWARD_SLASH;
import static com.services.chambitas.constant.FileConstant.JPG_EXTENSION;
import static com.services.chambitas.constant.FileConstant.USER_FOLDER;
import static com.services.chambitas.constant.FileConstant.USER_IMAGE_PATH;
import static com.services.chambitas.constant.UserImplConstant.EMAIL_ALREADY_EXISTS;
import static com.services.chambitas.constant.UserImplConstant.FOUND_USER_BY_USERNAME;
import static com.services.chambitas.constant.UserImplConstant.NO_USER_FOUND_BY_EMAIL;
import static com.services.chambitas.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.services.chambitas.domain.CityINEGI;
import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.PreferencesRH;
import com.services.chambitas.domain.PreferencesUser;
import com.services.chambitas.domain.RangeAmount;
import com.services.chambitas.domain.School;
import com.services.chambitas.domain.Skills;
import com.services.chambitas.domain.StateINEGI;
import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.UserPrincipal;
import com.services.chambitas.domain.WorkExperiences;
import com.services.chambitas.domain.dto.RegisterDTO;
import com.services.chambitas.domain.dto.SchoolDTO;
import com.services.chambitas.domain.dto.UpdateInformationRHDTO;
import com.services.chambitas.domain.dto.UserCVBasicDTO;
import com.services.chambitas.domain.dto.UserCVDTO;
import com.services.chambitas.domain.dto.UserCVPrincipalDTO;
import com.services.chambitas.domain.dto.UserRHCVDTO;
import com.services.chambitas.domain.dto.VisibilityDTO;
import com.services.chambitas.domain.dto.WorkExperiencesDTO;
import com.services.chambitas.enumeration.Role;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.exception.domain.UsernameExistException;
import com.services.chambitas.repository.ICityRepository;
import com.services.chambitas.repository.INotificationRepository;
import com.services.chambitas.repository.IPreferencesRHRepository;
import com.services.chambitas.repository.IPreferencesUserRepository;
import com.services.chambitas.repository.IRangeAmountRepository;
import com.services.chambitas.repository.ISchoolRepository;
import com.services.chambitas.repository.ISkillsRepository;
import com.services.chambitas.repository.IStateRepository;
import com.services.chambitas.repository.ITypeOfJobRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.repository.IWorkExperiencesRepository;
import com.services.chambitas.service.IPostulatesByOfferService;
import com.services.chambitas.service.IUserService;
import com.services.chambitas.utility.EmailService;
import com.services.chambitas.utility.LoginAttemptService;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private IUserRepository userRepository;
	private IWorkExperiencesRepository workExperiencesRepository;
	private ISchoolRepository schoolRepository;
	private ISkillsRepository skillRepository;

	private BCryptPasswordEncoder passwordEncoder;
	private LoginAttemptService loginAttemptService;
	private EmailService emailService;

	private IPreferencesRHRepository preRHRepository;
	private IPreferencesUserRepository preUserRepository;

	private ICityRepository cityRepository;
	private IStateRepository stateRepository;
	private ITypeOfJobRepository typeOfJobRepository;
	private IPostulatesByOfferService postulateService;

	private IPreferencesRHRepository prefRhRepository;

	private INotificationRepository notificationRepository;
	private IRangeAmountRepository rangeAmountRepository;

	@Autowired
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			LoginAttemptService loginAttemptService, EmailService emailService,
			IWorkExperiencesRepository workExperiencesRepository, ISchoolRepository schoolRepository,
			ISkillsRepository skillRepository, IPreferencesUserRepository preUserRepository,
			IPreferencesRHRepository preRHRepository, ICityRepository cityRepository, IStateRepository stateRepository,
			IRangeAmountRepository rangeAmountRepository, ITypeOfJobRepository typeOfJobRepository,
			IPostulatesByOfferService postulateService, IPreferencesRHRepository prefRhRepository,
			INotificationRepository notificationRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.loginAttemptService = loginAttemptService;
		this.emailService = emailService;
		this.workExperiencesRepository = workExperiencesRepository;
		this.schoolRepository = schoolRepository;
		this.skillRepository = skillRepository;
		this.preRHRepository = preRHRepository;
		this.preUserRepository = preUserRepository;
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
		this.rangeAmountRepository = rangeAmountRepository;
		this.postulateService = postulateService;
		this.typeOfJobRepository = typeOfJobRepository;
		this.notificationRepository = notificationRepository;
		this.prefRhRepository = prefRhRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
		} else {
			validateLoginAttempt(user);
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info(FOUND_USER_BY_USERNAME + username);
			return userPrincipal;
		}
	}

	@Override
	public User registerGeneral(RegisterDTO request) throws UsernameExistException {

		validateUsername(request.getUsername());
		User user = new User();
		String password = generatePassword();
		user.setNames(request.getNames());
		user.setSurnames(request.getNames() + " " + request.getFatherLastName() + " " + request.getMotherLastName());
		user.setMotherLastName(request.getMotherLastName());
		user.setFatherLastName(request.getFatherLastName());
		user.setJoinDate(new Date());
		user.setUsername(request.getUsername());
		user.setEmail(request.getUsername());
		user.setPassword(encodePassword(request.getPassword()));
		user.setCountry("MX");
		user.setActive(true);
		user.setNotLocked(true);
		user.setPublicProfile(true);
		user.setTypeOfUser(request.getTypeOfUser());

		if (request.getTypeOfUser() == 1) {
			user.setRole(getRoleEnumName("ROLE_USER").name());
			user.setAuthorities(getRoleEnumName("ROLE_USER").getAuthorities());
		}

		if (request.getTypeOfUser() == 2) {
			user.setRole(getRoleEnumName("ROLE_HR").name());
			user.setAuthorities(getRoleEnumName("ROLE_HR").getAuthorities());
		}

		userRepository.save(user);
		LOGGER.info("New user password: " + password);

		return user;
	}

	@Override
	public User createCVCompany(UserRHCVDTO request) throws GenericException, UserNotFoundException {

		CityINEGI city = existCity(request.getCity());
		StateINEGI state = existState(request.getState());
		TypeOfJob typeJob = existTypeJob(1L);

		User user = validateUpdateUsername(request.getUsername());

		user.setGender(request.getGender());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setNumberPhone(request.getNumberPhone());
		user.setSalary(null);
		user.setAboutMe(request.getAboutMe());
		user.setState(state);
		user.setCity(city);

		user.setJobTitle(request.getJobTitle());
		user.setPublicProfile(false);
		user.setModalidadTrabajo(typeJob);
		user.setFindJob("no");
		user.setRelocated("no");
		user.setBecomeRapidly(false);
		user.setProfileCompleted(true);

		PreferencesRH preRH = new PreferencesRH();

		preRH.setActitudesBlandas(request.getActitudesBlandas());
		preRH.setActitudesTecnicas(request.getActitudesBlandas());
		preRH.setAreasSpecialidad(request.getAreasSpecialidad());
		preRH.setUserId(user.getId());

		preRHRepository.save(preRH);
		userRepository.save(user);

		return user;
	}

	@Override
	public User createCVUser(UserCVDTO request) throws UserNotFoundException, GenericException {

		CityINEGI city = existCity(request.getCity());
		StateINEGI state = existState(request.getState());
		TypeOfJob typeJob = existTypeJob(request.getModalidadTrabajo());
		RangeAmount rangeAmount = existRangeAmount(request.getSalary());

		User user = validateUpdateUsername(request.getUsername());

		// Basic data
		user.setGender(request.getGender());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setNumberPhone(request.getNumberPhone());
		user.setSalary(rangeAmount);
		user.setAboutMe(request.getAboutMe());
		user.setState(state);
		user.setCity(city);
		user.setJobTitle(request.getJobTitle());
		user.setModalidadTrabajo(typeJob);
		user.setFindJob(request.getFindJob());
		user.setRelocated(request.getRelocated());

		user.setProfileCompleted(true);
		user.setPublicProfile(true);

//	    schoolRepository.deleteAll();

		for (String skill : request.getSkills()) {

			Skills e = new Skills();
			e.setValue(skill);
			e.setUserId(user.getId());

			skillRepository.save(e);
		}

		for (SchoolDTO e : request.getSchools()) {
			School r = new School();
			r.setBegins(e.getBegins());
			r.setEnds(e.getEnds());
			r.setName(e.getName());
			r.setSchoolName(e.getSchoolName());
			r.setType(e.getType());
			r.setUserId(user.getId());
			r.setWorked(e.isWorked());
			schoolRepository.save(r);
		}

		for (WorkExperiencesDTO wex : request.getExperiences()) {

			WorkExperiences e = new WorkExperiences();

			e.setBegins(wex.getBegins());
			e.setCompany(wex.getCompany());
			e.setCurrentlyWork(false);
			e.setDescription(wex.getDescription());
			e.setEnds(wex.getEnds());
			e.setJob(wex.getJob());
			e.setSkills(wex.getSkills());
			e.setUserId(user.getId());
			e.setWorked(wex.isWorked());

			workExperiencesRepository.save(e);
		}

		PreferencesUser preUser = new PreferencesUser();

		preUser.setFindJobs(request.getFindJobs());
		preUser.setLearnSkills(request.getLearnSkills());
		preUser.setUserId(user.getId());

		preUserRepository.save(preUser);
		userRepository.save(user);

		return user;
	}

	@Override
	public User updateCVPrincipal(UserCVPrincipalDTO request) throws UserNotFoundException, GenericException {

		CityINEGI city = existCity(request.getCity());
		StateINEGI state = existState(request.getState());
		TypeOfJob typeJob = existTypeJob(3L);

		User user = validateUpdateUsername(request.getUsername());

		user.setNames(request.getName());
		user.setFatherLastName(request.getApellidoPaterno());
		user.setMotherLastName(request.getApellidoMaterno());
		user.setSurnames(request.getName() + " " + request.getApellidoPaterno() + " " + request.getApellidoMaterno());

		user.setNumberPhone(request.getNumberPhone());
		user.setEmail(request.getEmail());
		user.setState(state);
		user.setCity(city);
		user.setDateOfBirth(request.getDateOfBirth());
		user.setRelocated(request.getRelocated());
		user.setModalidadTrabajo(typeJob);
		user.setNumberPhone(request.getNumberPhone());
		user.setGender(request.getGender());

		userRepository.save(user);

		return user;
	}

	@Override
	public User updateCVBasic(UserCVBasicDTO request) throws UserNotFoundException, GenericException {

		User user = validateUpdateUsername(request.getUsername());
		RangeAmount rangeAmount = existRangeAmount(request.getSalary());

		user.setSalary(rangeAmount);
		user.setAboutMe(request.getAboutMe());
		user.setJobTitle(request.getJobTitle());

		userRepository.save(user);

		return user;
	}

	@Override
	public User updateProfileImage(MultipartFile image, String username) throws IOException, UsernameExistException {
		if (image != null) {

			User user = findUserByUsername(username);

			Path userFolder = Paths.get(USER_FOLDER + username).toAbsolutePath().normalize();
			if (!Files.exists(userFolder)) {
				Files.createDirectories(userFolder);
				LOGGER.info("Dir" + userFolder);
			}

			Files.deleteIfExists(Paths.get(userFolder + username + DOT + JPG_EXTENSION));
			Files.copy(image.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION),
					REPLACE_EXISTING);
			user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
			userRepository.save(user);
			LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + image.getOriginalFilename());
			return user;

		} else {
			throw new UsernameExistException("Imagen necesaria");

		}

	}

	@Override
	public void resetPassword(String newPassword, String email, String token)
			throws MessagingException, EmailNotFoundException, GenericException {

		User user = userRepository.findUserByTokenAndUsername(token, email);

		if (user == null) {
			throw new GenericException("El token es incorrecto o bien ya fue utilizado.");
		}

		if (user.getExpireToken().before(new Date())) {
			throw new GenericException("El token se encuentra expirado!");
		}

		user.setToken("");
		user.setPassword(encodePassword(newPassword));
		userRepository.save(user);

	}

	@Override
	public void recoveryPassword(String email) throws EmailNotFoundException, MessagingException {

		User user = userRepository.findUserByUsername(email);

		if (user == null) {
			throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 1);
		Date expireToken = calendar.getTime();

		String token = generatePassword();
		user.setToken(token);
		user.setExpireToken(expireToken);
		userRepository.save(user);

		LOGGER.info("Token generate: " + token);
		emailService.resetPassword(user.getNames(), token, user.getUsername());
	}

	@Override
	public User desactiveProfile(String currentUsername) throws UserNotFoundException {
		User element = validateUpdateUsername(currentUsername);
		List<Notification> notifications = notificationRepository.findNotificationByUserMovil(currentUsername);
		notificationRepository.deleteAll(notifications);
		element.setActive(false);
		element.setRegBorrado(1);
		element.setPublicProfile(false);

		return element;
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> getAllUsersPaginate(int pageNo, int pageSize, String username, String names, String surnames) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> response = userRepository.searchByFilters(username, names, surnames, pageable);
		return response;
	}

	@Override
	public User updatePermissionsByUsername(String username, List<Permission> permissions) {
		User user = userRepository.findUserByUsername(username);
		user.setPermissions(permissions);
		userRepository.save(user);
		return user;
	}

	@Override
	public List<Permission> findPermissionsByUsername(String username) {
		List<Permission> list = userRepository.getPermissionsByUserName(username);
		return list;
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public void deleteUser(String username) throws IOException {
		User user = userRepository.findUserByUsername(username);
		Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
		FileUtils.deleteDirectory(new File(userFolder.toString()));
		userRepository.deleteById(user.getId());
	}

	@Override
	public User changeVisibility(VisibilityDTO request) throws UserNotFoundException {
		User user = validateUpdateUsername(request.getUsername());
		user.setPublicProfile(request.isValue());
		userRepository.save(user);
		return user;
	}

	private RangeAmount existRangeAmount(Long id) throws GenericException {
		RangeAmount response = rangeAmountRepository.findRangeAmountById(id);

		if (response == null) {
			throw new GenericException("No hemos encontrado el rango de sueldo!");
		}

		return response;
	}

//	
//	private User validateRfc(String rfc) throws GenericException {
//		
//		User response = userRepository.findUserByRfc(rfc);
//		
//		if(response != null) {
//			throw new GenericException("El RFC ya se encuentra registrado en la plataforma");
//		}
//		return response;
//	}
//	

	private User validateUpdateUsername(String currentUsername) throws UserNotFoundException {

		User currentUser = findUserByUsername(currentUsername);

		if (StringUtils.isNotBlank(currentUsername)) {
			if (currentUser == null) {
				throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}
		}

		return currentUser;
	}

	private CityINEGI existCity(Long id) throws GenericException {
		CityINEGI response = cityRepository.findCityINEGIById(id);

		if (response == null) {
			throw new GenericException("No hemos encontrado la ciudad!");
		}

		return response;
	}

	private StateINEGI existState(String id) throws GenericException {
		StateINEGI response = stateRepository.findStateINEGIByClave(id);

		if (response == null) {
			throw new GenericException("No hemos encontrado el estado!");
		}

		return response;
	}

	private void validateUsername(String email) throws UsernameExistException {
		User user = userRepository.findUserByUsername(email);
		if (user != null) {
			throw new UsernameExistException(EMAIL_ALREADY_EXISTS);
		}
	}

	private Role getRoleEnumName(String role) {
		return Role.valueOf(role.toUpperCase());
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(23);
	}

	private String getTemporaryProfileImageUrl(String username) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username)
				.toUriString();
	}

	private String setProfileImageUrl(String username) {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(USER_IMAGE_PATH + username + FORWARD_SLASH + username + DOT + JPG_EXTENSION).toUriString();
	}

	private void validateLoginAttempt(User user) {
		if (user.isNotLocked()) {
			if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				user.setNotLocked(false);
			} else {
				user.setNotLocked(true);
			}
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
	}

	private TypeOfJob existTypeJob(Long clave) throws GenericException {
		TypeOfJob element = typeOfJobRepository.findTypeOfJobById(clave);

		if (element == null) {
			throw new GenericException("No se encontro el recurso");
		}

		return element;
	}

	@Override
	public Page<User> searchByFiltersWEB(String city, String state, String salary, String job, String mod, int pageNo,
			int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> response = userRepository.searchByFiltersWEB(city, state, salary, job, mod, pageable);
		return response;
	}

	@Override
	public User updatePreferencesRH(UpdateInformationRHDTO request) {

		User user = userRepository.findUserById(request.getId());
		PreferencesRH pre = prefRhRepository.getPreferencesRHByUser(request.getId());

		user.setNames(request.getNames());
		user.setSurnames(request.getNames() + " " + request.getFatherLastName() + " " + request.getMotherLastName());
		user.setMotherLastName(request.getMotherLastName());
		user.setFatherLastName(request.getFatherLastName());
		user.setEmail(request.getEmailContact());
		user.setNumberPhone(request.getNumberPhone());
		user.setGender(request.getGender());
		user.setDateOfBirth(request.getFechaNacimiento());
		user.setJobTitle(request.getJobTitle());
		
		pre.setActitudesBlandas(request.getActitudesBlandas());
		pre.setActitudesTecnicas(request.getActitudesTecnicas());
		pre.setAreasSpecialidad(request.getAreasSpecialidad());
		
		prefRhRepository.save(pre);
		userRepository.save(user);

		return user;
	}

	@Override
	public PreferencesRH getPreferencesRH(Long userId) throws GenericException {

		PreferencesRH pre = prefRhRepository.getPreferencesRHByUser(userId);

		if (pre == null) {
			throw new GenericException("No se encontro el recurso");
		}

		return pre;
	}

	@Override
	public User findUserById(Long username) throws UsernameExistException {
		
		User user = userRepository.findUserById(username);
		
		if (user == null) {
			throw new UsernameExistException(EMAIL_ALREADY_EXISTS);
		}
		
		return user;
	}

}
