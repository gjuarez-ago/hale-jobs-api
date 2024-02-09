package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.TypeOfPayment;
import com.services.chambitas.domain.dto.TypeOfPaymentDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ITypeOfPaymentService {
			
    TypeOfPayment createTypeOfPayment(TypeOfPaymentDTO request);
	
    TypeOfPayment editTypeOfPayment(String id, TypeOfPaymentDTO request) throws GenericException;
	
	TypeOfPayment deleteTypeOfPayment(String id) throws GenericException;

	TypeOfPayment findTypeOfPayment(String id) throws GenericException;
	
	List<TypeOfPayment> getAllTypeOfPayments();
	 
    Page<TypeOfPayment> getAllTypeOfPaymentsByAdmin(int pageNo, int pageSize);
    
}
