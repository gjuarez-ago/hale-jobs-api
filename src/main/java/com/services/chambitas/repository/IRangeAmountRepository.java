package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.RangeAmount;

public interface IRangeAmountRepository extends JpaRepository<RangeAmount, Long> {
	
	RangeAmount findRangeAmountById(Long id);

}
