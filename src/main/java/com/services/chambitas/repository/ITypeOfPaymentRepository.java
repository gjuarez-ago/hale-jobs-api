package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.TypeOfPayment;

public interface ITypeOfPaymentRepository extends JpaRepository<TypeOfPayment, Long>{
	
    TypeOfPayment findTypeOfPaymentByClave(String clave);
    
    TypeOfPayment findTypeOfPaymentById(Long id);
    
    
    
}

