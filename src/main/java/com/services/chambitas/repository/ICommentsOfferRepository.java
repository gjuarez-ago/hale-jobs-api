package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.CommentsOffer;

public interface ICommentsOfferRepository extends JpaRepository<CommentsOffer, Long>{
	
     CommentsOffer findCommentsOfferByConsecutive(String consecutive);
     
     @Query(value = "SELECT c.* FROM comments_offer AS c WHERE c.consecutive = :consecutive AND c.reg_borrado = 0",nativeQuery = true)
 	 CommentsOffer findCommentsByIdMovil(@Param("consecutive") String consecutive);
     
     @Query(value = "SELECT c.* FROM comments_offer AS c WHERE c.offer_id = :offerId AND c.reg_borrado = 0",nativeQuery = true)
 	 List<CommentsOffer> findCommentsByOfferMovil(@Param("offerId") String offerId);
     
     @Query(value = "SELECT c.* FROM comments_offer AS c WHERE c.offer_id = :offerId AND c.reg_borrado = 0",nativeQuery = true)
  	 Page<CommentsOffer> findCommentsByOfferWEB(@Param("offerId") String offerId, Pageable pageable);
     
     @Query(value = "SELECT c.* FROM comments_offer AS c WHERE c.offer_id LIKE %:keyword% AND c.reg_borrado = 0",nativeQuery = true)
  	 Page<CommentsOffer> findCommentsByOfferAdmin(@Param("keyword") String keyword, Pageable pageable);
     
     
}
