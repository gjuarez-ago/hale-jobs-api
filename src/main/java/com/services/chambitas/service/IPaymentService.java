package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Payment;
import com.services.chambitas.domain.dto.PaymentDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IPaymentService {
	
	Payment generatePayment(PaymentDTO request);
	
	Page<Payment> getPaymentsByAdmin(int pageNo, int pageSize);
	
	Page<Payment> getPaymentsByUser(int pageNo, int pageSize,Long userId);
	
	List<Payment> getPaymentByLoanIdM(String loanId);

	Payment viewPaymentByConsecutive(String consecutive) throws GenericException;
	
	
}
