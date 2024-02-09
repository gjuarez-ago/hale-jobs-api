package com.services.chambitas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Complaints;

public interface IComplaintRepository extends JpaRepository<Complaints, Long>{

	Complaints findComplaintsById(Long id);
	
    @Query(value = "SELECT * FROM complaints c WHERE c.title LIKE %:title% AND c.comments LIKE %:comments%",nativeQuery = true)
	Page<Complaints> getAllComplaintsOfferByUserRH(@Param("title") String title, @Param("comments") String comments, Pageable pageable);
	
	@Query(value = "SELECT * FROM complaints AS po WHERE po.user_id = :userId AND po.offer_id = :offerId",nativeQuery = true)
	Complaints findComplaintsByUserAndOffer(@Param("userId") Long userId, @Param("offerId") Long offerId);
	
	@Query(value = "SELECT p FROM Complaints p JOIN p.offer o WHERE p.title LIKE %:keyword% AND o.id = :offer AND o.status = 0 ORDER BY p.regDateCreated ASC")
	Page<Complaints> findComplaintsByOffer(@Param("keyword") String keyword, @Param("offer") Long offerId,  Pageable pageable);
	
}

