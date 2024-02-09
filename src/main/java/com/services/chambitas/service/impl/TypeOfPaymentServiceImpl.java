package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.TypeOfPayment;
import com.services.chambitas.domain.dto.TypeOfPaymentDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ITypeOfPaymentRepository;
import com.services.chambitas.service.ITypeOfPaymentService;

@Service
@Transactional
public class TypeOfPaymentServiceImpl implements ITypeOfPaymentService{
	
	@Autowired
	private ITypeOfPaymentRepository repository;

	@Override
	public TypeOfPayment createTypeOfPayment(TypeOfPaymentDTO request) {
		
		TypeOfPayment element = new TypeOfPayment();
		
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		
		repository.save(element);
		return element;
	}

	@Override
	public TypeOfPayment editTypeOfPayment(String id, TypeOfPaymentDTO request) throws GenericException {
	
		TypeOfPayment element = existTypePayment(id);
		element.setClave(request.getClave());
		element.setValor(request.getValor());
		repository.save(element);
		
		return element;
	}

	@Override
	public TypeOfPayment deleteTypeOfPayment(String id) throws GenericException {
		TypeOfPayment element = existTypePayment(id);
		repository.deleteById(element.getId());
		return element;
	}

	@Override
	public TypeOfPayment findTypeOfPayment(String id) throws GenericException {
		TypeOfPayment element = existTypePayment(id);
		return element;
	}
	

	@Override
	public List<TypeOfPayment> getAllTypeOfPayments() {
		return repository.findAll();
	}

	@Override
	public Page<TypeOfPayment> getAllTypeOfPaymentsByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<TypeOfPayment> response = repository.findAll(pageable);
		return response;
	}
	
	private TypeOfPayment existTypePayment(String clave) throws GenericException {
		TypeOfPayment element = repository.findTypeOfPaymentByClave(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}


}
