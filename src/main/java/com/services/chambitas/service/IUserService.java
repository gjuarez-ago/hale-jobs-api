package com.services.chambitas.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.PreferencesRH;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.RegisterDTO;
import com.services.chambitas.domain.dto.UpdateInformationRHDTO;
import com.services.chambitas.domain.dto.UserCVBasicDTO;
import com.services.chambitas.domain.dto.UserCVDTO;
import com.services.chambitas.domain.dto.UserCVPrincipalDTO;
import com.services.chambitas.domain.dto.UserRHCVDTO;
import com.services.chambitas.domain.dto.VisibilityDTO;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.exception.domain.UsernameExistException;

@Service
public interface IUserService {
	
	    User registerGeneral(RegisterDTO request) throws UsernameExistException;
	
	    User createCVCompany(UserRHCVDTO request) throws GenericException, UserNotFoundException;
	    
	    User createCVUser(UserCVDTO request) throws UserNotFoundException, GenericException;
	    	    
	    User updateCVPrincipal(UserCVPrincipalDTO request) throws UserNotFoundException, GenericException;
	    
	    User updateCVBasic(UserCVBasicDTO request) throws UserNotFoundException, GenericException;
	    
	    User updateProfileImage(MultipartFile image, String username) throws IOException, UsernameExistException;
	    
	    void resetPassword(String newPassword, String numberPhone, String token) throws MessagingException, EmailNotFoundException, GenericException;
	    
	    void recoveryPassword(String token) throws MessagingException, EmailNotFoundException;
	   
	    List<User> getUsers();
	    
	    Page<User> getAllUsersPaginate(int pageNo, int pageSize, String username, String names, String surnames);

	    User findUserByUsername(String username);
	    
	    User findUserById(Long username) throws UsernameExistException;
	    
	    User desactiveProfile(String username) throws UserNotFoundException;
	    
	    User changeVisibility(VisibilityDTO request) throws UserNotFoundException;
	    
	    void deleteUser(String username) throws IOException;
	    
	    User updatePermissionsByUsername(String username, List<Permission> permissions);
	    
	    List<Permission> findPermissionsByUsername(String username);
	    
	    Page<User> searchByFiltersWEB(String city,String state,String salary, String job, String mod, int pageNo, int pageSize);
	  
	    User updatePreferencesRH(UpdateInformationRHDTO request);
	    
	    PreferencesRH getPreferencesRH(Long userId) throws GenericException;
	   
	
}
