package com.services.chambitas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Certification;
import com.services.chambitas.domain.Skills;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ICertificationService {
	
    List<Certification> listByUser(Long user);
	
    Certification createByUser(Certification request);
	
    Certification updateByUser(Long id, Certification request) throws GenericException;
	
    Certification deleteById(Long id) throws GenericException;
	
    Certification findById(Long id) throws GenericException;
	

}
