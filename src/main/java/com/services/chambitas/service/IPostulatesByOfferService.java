package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.PostulatesOffer;
import com.services.chambitas.domain.dto.PostulateByOfferDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IPostulatesByOfferService {
	
	// Postularme a una oferta
	PostulatesOffer createPostulation(PostulateByOfferDTO request) throws GenericException;
	
	Notification messagePostulate(PostulateByOfferDTO request) throws GenericException;
	
	// Editar postulación
	PostulatesOffer changeStatus(Long id, PostulateByOfferDTO request) throws GenericException;
	
	// Eliminar postulación
	PostulatesOffer deletePostulation(Long id, Long userId) throws GenericException;
	
	// Buscar postulación por ID
	PostulatesOffer findPostulationById(Long id) throws GenericException;
	
	// Buscar postulaciones por usuario movil
	List<PostulatesOffer> getPostulatesByUserIdM(Long userId);
	
	// Buscar postulaciones por usuario web
	Page<PostulatesOffer> getPostulatesByUserIdW(String keyword, Long userId,  int pageNo, int pageSize);

	// Buscar postulaciones por oferta movil
	List<PostulatesOffer> getAllPostulatesByOfferM(Long offerId);
	
	// Buscar postulaciones por oferta web
	Page<PostulatesOffer> getAllPostulatesByOfferW(String key, Long offerId, int pageNo, int pageSize);
	
	// Visualizar todas las postulaciones por administrador
	Page<PostulatesOffer> getAllPostulatesAdmin(String keyword,  int pageNo, int pageSize);

}
