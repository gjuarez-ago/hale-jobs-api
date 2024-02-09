package com.services.chambitas.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.EventsUser;
import com.services.chambitas.domain.dto.EventsUserDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IEventUsersService {
	
	EventsUser generateEvent(EventsUserDTO request) throws GenericException;
	
	EventsUser deleteEvent(Long id) throws GenericException;
	
	EventsUser updateEvent(Long id, EventsUserDTO request) throws GenericException;
	
	Page<EventsUser> getAllEventsByUserRecruiter(Long userId, int pageNo, int pageSize);
	
	Page<EventsUser> getAllEventsByUserGuest(Long userId, int pageNo, int pageSize);
	
	Page<EventsUser> getAllEventsByAdmin(int pageNo, int pageSize);
	
}
