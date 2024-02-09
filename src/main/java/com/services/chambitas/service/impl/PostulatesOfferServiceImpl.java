package com.services.chambitas.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.PostulatesOffer;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.PostulateByOfferDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.INotificationRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IPostulatesOfferRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IPostulatesByOfferService;

@Service
@Transactional
public class PostulatesOfferServiceImpl implements IPostulatesByOfferService{

	@Autowired
	private IPostulatesOfferRepository postulatesOfferRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired 
	private IOfferRepository offerRepository;
	
	@Autowired 
	private INotificationRepository noticationRespository;
	
	@Override
	public PostulatesOffer createPostulation(PostulateByOfferDTO request) throws GenericException {
		
		PostulatesOffer element = new PostulatesOffer();
		
		Offer offer = exisOffer(request.getOfferId());
		existPostulation(request.getUserId(),offer.getId());		
		User user =  existUser(request.getUserId());

		element.setConsecutive(generateConsecutive());
		element.setOffer(offer);
		element.setUser(user);
		element.setStatus(0);
		element.setCompleted(false);
		element.setComments(request.getComments());
		
		element.setRegBorrado(0);
		element.setRegCreatedBy(user.getId());
		element.setRegDateCreated(new Date());
		
		postulatesOfferRepository.save(element);
		createNotication("Se ha registrado un cambio en tu oferta " + offer.getTitle(), "El usuario " + user.getUsername() + " se ha postulado a tu vacante " + offer.getTitle(), offer.getUser().getEmail(), user.getUsername(), element.getOffer().getId(), user.getId(), "OFERTAS");

		
		return element;
	}

	@Override
	public PostulatesOffer changeStatus(Long id, PostulateByOfferDTO request) throws GenericException {
		
		PostulatesOffer element =  existPostulatesOffer(id);
		User user =  existUser(request.getUserId());

		String r = "";
		
		element.setStatus(request.getStatus());
		element.setRegDateUpdated(new Date());
		element.setRegUpdateBy(request.getUserId());
		element.setCompleted(true);
		
		if(request.getStatus() == 1) {
		  r = "¡Felicides!. Han decidido continuar el proceso contigo, por lo que te recomendamos estar pendiente a tus medios de comunicación que indicaste en tu CV";	
		}
		
		if(request.getStatus() == 2) {
		r = "Lo sentimos, pero al parecer han decidido no continuar el proceso contigo, te pedimos no te desanimes y te agradecemos formar parte de este proceso, te recomendamos buscar mas ofertas dentro de nuestro portal.";	
		}

		
		postulatesOfferRepository.save(element);	
		createNotication("Se ha registrado un cambio en tu postulación '" + element.getOffer().getTitle() + "'", r, element.getUser().getUsername(), user.getUsername(), element.getOffer().getId(), user.getId(), "OFERTAS");

		return element;
	}

	@Override
	public PostulatesOffer deletePostulation(Long id, Long userId) throws GenericException {
		PostulatesOffer element =  existPostulatesOffer(id);
		element.setRegBorrado(1);
		postulatesOfferRepository.save(element);
		return element;
	}

	@Override
	public PostulatesOffer findPostulationById(Long id) throws GenericException {
		PostulatesOffer element =  existPostulatesOffer(id);
		return element;
	}

	@Override
	public List<PostulatesOffer> getPostulatesByUserIdM(Long userId) {
		List<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByUserMovil(userId); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getPostulatesByUserIdW(String keyword, Long userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByUserWEB(keyword, userId, pageable); 
		return list;
	}

	@Override
	public List<PostulatesOffer> getAllPostulatesByOfferM(Long offerId) {
		List<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferMovil(offerId); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getAllPostulatesByOfferW(String keyword,Long offerId,  int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferWEB(keyword, offerId, pageable); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getAllPostulatesAdmin(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findAll(pageable); 
		return list;
	}

	
	
	// Privates methods
	private PostulatesOffer existPostulatesOffer(Long consecutive) throws GenericException {
		
		PostulatesOffer postulate = postulatesOfferRepository.findPostulateById(consecutive);
		
		if(postulate == null) {
			throw new GenericException("No se encontro la postulación");
		}
		
		return postulate;
	}
	
	
	private PostulatesOffer existPostulation(Long userId, Long offerId) throws GenericException {
	     
		PostulatesOffer postulate = postulatesOfferRepository.findPostulateByUserAndOffer(userId, offerId);
		
		if(postulate != null) {
			throw new GenericException("Ya te has postulado a esta vacante.");
		}
		
		return postulate;
	}
	
	
	private User existUser(Long id) throws GenericException {
		User user = userRepository.findUserById(id);
		if(user == null) {throw new GenericException("No se encontro el usuario");}
		return user;
	}
	
	private Offer exisOffer(Long consecutive) throws GenericException {
		Offer offer = offerRepository.findOfferById(consecutive);
		if(offer ==  null) {throw new GenericException("No se encontro la oferta");}
		return offer;
	}

	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = postulatesOfferRepository.count();
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
	
	
	
	private Notification createNotication(String title, String message, String emailDest, String sendBy, Long offer, Long user, String typeAd) {
		
		UUID uuid=UUID.randomUUID();   
		
		Notification n = new Notification();
		n.setConsecutive(uuid.toString());
		n.setContent(message);
		n.setEmailDestination(emailDest);
		n.setOfferId(offer);
		n.setSendBy(sendBy);
		n.setTitle(title);
		n.setTypeAD("OFERTAS");
		n.setUserId(user);
		n.setRegBorrado(0);
		n.setRegDateCreated(new Date());
		n.setStatus(0);
		noticationRespository.save(n);
		return n;
	}

	@Override
	public Notification messagePostulate(PostulateByOfferDTO request) throws GenericException {
		
		Offer offer = exisOffer(request.getOfferId());
		User user =  existUser(request.getUserId());

	    UUID uuid=UUID.randomUUID();   
		
		Notification n = new Notification();
		n.setConsecutive(uuid.toString());
		n.setContent(request.getComments());
		n.setEmailDestination(user.getUsername());
		n.setOfferId(request.getOfferId());
		n.setSendBy(user.getUsername());
		n.setTitle("Un reclutador tiene un mensaje para ti relacionado a la siguiente oferta: " + offer.getTitle());
		n.setTypeAD("MENSAJE");
		n.setRegBorrado(0);
		n.setRegDateCreated(new Date());
		n.setUserId(request.getUserId());
		n.setStatus(0);
		noticationRespository.save(n);
		
		return null;
	}
	
}
