package com.services.chambitas.controller;

import static com.services.chambitas.constant.FileConstant.FORWARD_SLASH;
import static com.services.chambitas.constant.FileConstant.USER_FOLDER;
import static com.services.chambitas.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.PreferencesRH;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.UserPrincipal;
import com.services.chambitas.domain.dto.LoginDTO;
import com.services.chambitas.domain.dto.RecoveryPasswordDTO;
import com.services.chambitas.domain.dto.RegisterDTO;
import com.services.chambitas.domain.dto.ResetPasswordDTO;
import com.services.chambitas.domain.dto.UpdateInformationRHDTO;
import com.services.chambitas.domain.dto.UserCVBasicDTO;
import com.services.chambitas.domain.dto.UserCVDTO;
import com.services.chambitas.domain.dto.UserCVPrincipalDTO;
import com.services.chambitas.domain.dto.UserRHCVDTO;
import com.services.chambitas.domain.dto.VisibilityDTO;
import com.services.chambitas.domain.response.HttpResponse;
import com.services.chambitas.exception.domain.EmailExistException;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.exception.domain.UsernameExistException;
import com.services.chambitas.service.IUserService;
import com.services.chambitas.utility.JWTTokenProvider;

@RestController
@RequestMapping(path = { "/user"})
public class UserController {
	
	  public static final String EMAIL_SENT = "An email with a new password was sent to: ";
	  public static final String PASSWORD_RESET = "Contraseña restablecida correctamente";
	  public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	  private AuthenticationManager authenticationManager;
	  private IUserService userService;
	  private JWTTokenProvider jwtTokenProvider;

	  @Autowired
	    public UserController(AuthenticationManager authenticationManager, IUserService userService, JWTTokenProvider jwtTokenProvider) {
	        this.authenticationManager = authenticationManager;
	        this.userService = userService;
	        this.jwtTokenProvider = jwtTokenProvider;
	  }
	  
	  @PostMapping("/login")
	  public ResponseEntity<User> login(@RequestBody LoginDTO user) throws JsonMappingException, JsonProcessingException {

		authenticate(user.getUsername(), user.getPassword());
		User loginUser = userService.findUserByUsername(user.getUsername());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);

		 return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
		}
		
	   @PostMapping("/register")
	    public ResponseEntity<User> register(@RequestBody RegisterDTO user) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException {
	        User newUser = userService.registerGeneral(user);
	        return new ResponseEntity<>(newUser, OK);
 	   }
	   
	   @PostMapping("/create-cv-user-out")
	    public ResponseEntity<User> generateCV(@RequestBody UserCVDTO request) throws  GenericException, UserNotFoundException {
	        User user = userService.createCVUser(request);
	        return new ResponseEntity<>(user, OK);
	   }
	   
	   
	   @PostMapping("/create-cv-company")
	    public ResponseEntity<User> registerCompany(@RequestBody UserRHCVDTO request) throws  GenericException, UserNotFoundException {
	        User newUser = userService.createCVCompany(request);
	        return new ResponseEntity<>(newUser, OK);
	   }
	   
	   @PostMapping("/change-visibility")
	    public ResponseEntity<User> changeVisibility(@RequestBody VisibilityDTO request) throws  GenericException, UserNotFoundException {
	        User newUser = userService.changeVisibility(request);
	        return new ResponseEntity<>(newUser, OK);
	   }
	   
	   @PostMapping("/update-cv-principal")
	    public ResponseEntity<User> updateCV(@RequestBody UserCVPrincipalDTO request) throws  GenericException, UserNotFoundException {
	        User user = userService.updateCVPrincipal(request);
	        return new ResponseEntity<>(user, OK);
	   }
	   
	   @PostMapping("/update-cv-basic")
	    public ResponseEntity<User> updateCV(@RequestBody UserCVBasicDTO request) throws  GenericException, UserNotFoundException {
	        User user = userService.updateCVBasic(request);
	        return new ResponseEntity<>(user, OK);
	   }
	 
	   
	   @DeleteMapping("/desactivate-profile/{username}")
	   public ResponseEntity<User> desactivateProfile(@PathVariable("username") String username) throws UserNotFoundException {
		   User response = userService.desactiveProfile(username);
		   return new ResponseEntity<User>(response, HttpStatus.OK);
	   }
	  	    
	
	    @GetMapping("/find/{username}")
	    public ResponseEntity<User> getUser(@PathVariable("username") String username) throws UserNotFoundException {
	        User user = userService.findUserByUsername(username);
	        return new ResponseEntity<>(user, OK);
	    }
	    
		
	    @GetMapping("/find-by-id/{id}")
	    public ResponseEntity<User> getUserByID(@PathVariable("id") Long username) throws UserNotFoundException, UsernameExistException {
	        User user = userService.findUserById(username);
	        return new ResponseEntity<>(user, OK);
	    }

	

	    @GetMapping("/list")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userService.getUsers();
	        return new ResponseEntity<>(users, OK);
	    }
	    
	    @GetMapping("/paginate") 
		public ResponseEntity<Page<User>> paginate(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
	            @RequestParam(value = "username", defaultValue = "", required = false) String username,
	            @RequestParam(value = "names", defaultValue = "", required = false) String names,
	            @RequestParam(value = "surnames", defaultValue = "", required = false) String surnames) {
			Page<User> response = userService.getAllUsersPaginate(pageNo, pageSize, username, names, surnames);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}
	    
	    @PostMapping("/update-profile-image")
	    public ResponseEntity<User> updateProfile(@RequestParam(value = "username") String username, @RequestParam(value = "image") MultipartFile image) throws MessagingException, EmailNotFoundException, IOException, UsernameExistException {
	    	User response = userService.updateProfileImage(image, username);
			return new ResponseEntity<>(response, HttpStatus.OK);	
	    }

	    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
		public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName)
				throws IOException {
			return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
		}

	    
	    @PostMapping("/recovery-password")
	    public ResponseEntity<HttpResponse> recoveryPassword(@RequestBody RecoveryPasswordDTO request) throws MessagingException, EmailNotFoundException {
	        userService.recoveryPassword(request.getEmail());
	        return response(OK, EMAIL_SENT + request.getEmail());
	    }
	    
	    @PostMapping("/update-pre-rh")
	    public ResponseEntity<User> updatePreferencesRH(@RequestBody UpdateInformationRHDTO request) throws MessagingException, EmailNotFoundException {
	    	User response = userService.updatePreferencesRH(request);
			return new ResponseEntity<>(response, HttpStatus.OK);	
	    }
	    
	    @GetMapping("/find-pre-rh/{key}")
	    public ResponseEntity<PreferencesRH> getUser(@PathVariable("key") Long id) throws UserNotFoundException, GenericException {
	    	PreferencesRH user = userService.getPreferencesRH(id);
	        return new ResponseEntity<>(user, OK);
	    }

	    
	    @PostMapping("/reset-password")
	    public ResponseEntity<HttpResponse> resetPassword(@RequestBody ResetPasswordDTO request) throws MessagingException, EmailNotFoundException, GenericException {
	    	userService.resetPassword(request.getPassword(), request.getEmail(), request.getToken());
	    	 return response(OK, PASSWORD_RESET);
	    }
	    
	    @DeleteMapping("/delete/{username}")
	    @PreAuthorize("hasAnyAuthority('user:delete')")
	    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username) throws IOException {
	        userService.deleteUser(username);
	        return response(OK, USER_DELETED_SUCCESSFULLY);
	    }
	    
		@PostMapping("/update-permissions/{username}")
		public ResponseEntity<User> updatePermissions(@PathVariable("username") String username,
				@RequestBody List<Permission> permissions) {
			User response = userService.updatePermissionsByUsername(username, permissions);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		@GetMapping("/list-permissions/{username}")
		public ResponseEntity<List<Permission>> getPermissionByUsername(@PathVariable("username") String username) {
			List<Permission> response = userService.findPermissionsByUsername(username);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		@GetMapping("/search-rh")
		public ResponseEntity<Page<User>> searchByFiltersWEB(
				@RequestParam(value = "state", defaultValue = "", required = false) String state,
				@RequestParam(value = "city", defaultValue = "", required = false) String city,
				@RequestParam(value = "salary", defaultValue = "", required = false) String salary,
				@RequestParam(value = "jobTitle", defaultValue = "", required = false) String jobTitle,
				@RequestParam(value = "mod", defaultValue = "", required = false) String modalidadTrabajo,
				@RequestParam(value = "speciality", defaultValue = "", required = false) String especialidad,
				@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
				@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

			Page<User> response = userService.searchByFiltersWEB(city,state,salary,  jobTitle,  modalidadTrabajo, pageNo, pageSize);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
	    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
	        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
	                message), httpStatus);
	    }

	    private HttpHeaders getJwtHeader(UserPrincipal user) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
	        return headers;
	    }

	    private void authenticate(String username, String password) {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    }

}