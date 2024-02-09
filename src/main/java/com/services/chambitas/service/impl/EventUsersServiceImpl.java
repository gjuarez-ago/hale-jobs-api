package com.services.chambitas.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.EventsUser;
import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.EventsUserDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IEventUserRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IEventUsersService;


@Service
@Transactional
public class EventUsersServiceImpl implements IEventUsersService{
		
	@Autowired
	private IEventUserRepository repository;
	
	@Autowired 
	private IUserRepository userRepository;
	
	@Autowired
	private IOfferRepository offerRepository;
	
	@Override
	public EventsUser generateEvent(EventsUserDTO request) throws GenericException {
		
		Offer offer = existOffer(request.getOffer());
		User userR = exisUser(request.getUserRecruiter());
		User userG = exisUser(request.getUserGuest());
		
		EventsUser element = new EventsUser();
		
		element.setDateBegin(request.getDateBegin());
		element.setDescription(request.getDescription());
		element.setOffer(offer);
		element.setRegDateCreated(new Date());
		element.setStatus(0);
		element.setTitle(request.getTitle());
		element.setUrlMeet(request.getUrlMeet());
		element.setUserGuest(userG);
		element.setUserRecruiter(userR);
		
		return element;
	}

	@Override
	public EventsUser deleteEvent(Long id) throws GenericException {
		EventsUser element = existEvent(id);
		repository.deleteById(id);
		return element;
	}

	@Override
	public EventsUser updateEvent(Long id, EventsUserDTO request) throws GenericException {
		EventsUser element = existEvent(id);
		return element;
	}

	@Override
	public Page<EventsUser> getAllEventsByUserRecruiter(Long userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<EventsUser> response = repository.getAllEventsByUserRecruiter(userId,pageable);
		return response;
	}

	@Override
	public Page<EventsUser> getAllEventsByUserGuest(Long userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<EventsUser> response = repository.getAllEventsByUserGuest(userId, pageable);
		return response;
	}

	@Override
	public Page<EventsUser> getAllEventsByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<EventsUser> response = repository.findAll(pageable);
		return response;
	}
	
	private EventsUser existEvent(Long id) throws GenericException {
		
		EventsUser element = repository.findEventsUserById(id);
		if(element == null ) {
			throw new GenericException("No encontramos el evento");
		}	
		return element;
	}
	

	private Offer existOffer(Long id) throws GenericException {
		
		Offer element = offerRepository.findOfferById(id);
		if(element == null ) {
			throw new GenericException("No encontramos la oferta");
		}
		return element;
	}
	
	private User exisUser(Long id) throws GenericException {
		
		User element = userRepository.findUserById(id);
		
		if(element == null ) {
			throw new GenericException("No se encuentra disponible el usuario");
		}
		
		return element;
	}

}
