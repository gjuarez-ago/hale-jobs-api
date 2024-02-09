package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Payment;

public interface IPaymentRepository extends JpaRepository<Payment,Long>{
	
	Payment findPaymentByConsecutive(String consecutive);
	
	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive = :loanId AND p.reg_borrado = 0",nativeQuery = true)
	List<Payment> findPaymentsByLoanIdM(@Param("loanId") String loanId);
	
//	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive = :loanId AND p.reg_borrado = 0",nativeQuery = true)
//	List<Payment> findPaymentsByLoanIdM(@Param("loanId") String loanId);
	
	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE l.consecutive = :loanId AND p.reg_borrado = 0",nativeQuery = true)
	Page<Payment> findPaymentsByLoanIdW(@Param("loanId") Long userId, Pageable pageable);	
	
	@Query(value = "SELECT p.* FROM payment AS p INNER JOIN loan AS l ON l.id = p.prestamo_id AND l.reg_borrado = 0 WHERE  p.reg_borrado = 0",nativeQuery = true)
	Page<Payment> findPaymentsByLoanIdWA(Pageable pageable);
		
}