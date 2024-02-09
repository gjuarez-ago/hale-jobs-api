package com.services.chambitas.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Payment;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.PaymentDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IPaymentRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IPaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService{

	@Autowired 
	private IUserRepository userRepository;
	
	@Autowired
	private IPaymentRepository repository;

	@Override
	public Payment generatePayment(PaymentDTO request) {
		
		String consecutive = generateConsecutive();
		User user = userRepository.findUserById(request.getUserId());
		
		Payment entity = new Payment();
		entity.setConsecutive(consecutive);
		entity.setAmount(request.getAmount());
		entity.setMethodPayment(request.getMethodPayment());
		entity.setReferenceId(request.getIdReference());
		entity.setStatusReference(request.getStatusReference());
		entity.setEstatus(request.getEstatus());
		entity.setCurrency(request.getCurrency());
		entity.setUser(user);
		
		entity.setRegBorrado(0);
		entity.setRegCreatedBy(request.getUserId());
		entity.setRegDateCreated(new Date());
		repository.save(entity);
		return entity;
	}
	
	
	@Override
	public Page<Payment> getPaymentsByUser(int pageNo, int pageSize,Long userId) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Payment> response = repository.findPaymentsByLoanIdW(userId, pageable);
		return response;	
	}
	
	@Override
	public List<Payment> getPaymentByLoanIdM(String loanId) {
		return repository.findPaymentsByLoanIdM(loanId);
	}

	@Override
	public Payment viewPaymentByConsecutive(String consecutive) throws GenericException {
		return existPayment(consecutive);
	}
	
	@Override
	public Page<Payment> getPaymentsByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Payment> response = repository.findPaymentsByLoanIdWA(pageable);
		return response;
	}

	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = repository.count();
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
	
	private Payment existPayment(String consecutive) throws GenericException {
		
		Payment element = repository.findPaymentByConsecutive(consecutive);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}


	

}
