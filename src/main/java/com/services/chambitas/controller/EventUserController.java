package com.services.chambitas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.EventsUser;
import com.services.chambitas.domain.dto.EventsUserDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IEventUsersService;

@RestController
@RequestMapping(path = { "/event-users" })
public class EventUserController {

	@Autowired
	private IEventUsersService service;

	@PostMapping("/create")
	public ResponseEntity<EventsUser> generateEvent(@RequestBody EventsUserDTO request) throws GenericException {
		EventsUser response = service.generateEvent(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EventsUser> deleteEvent(@PathVariable(value = "id") Long id) throws GenericException {
		EventsUser response = service.deleteEvent(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<EventsUser> updateEvent(@RequestBody EventsUserDTO request) throws GenericException {
		EventsUser response = service.generateEvent(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get-by-user-recruiter")
	public ResponseEntity<Page<EventsUser>> getAllEventsByUserRecruiter(@RequestParam("userId") Long userId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<EventsUser> response = service.getAllEventsByUserRecruiter(userId, pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get-by-user-guest")
	public ResponseEntity<Page<EventsUser>> getAllEventsByUserGuest(@RequestParam("userId") Long userId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<EventsUser> response = service.getAllEventsByUserGuest(userId, pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/get-by-user-admin")
	public ResponseEntity<Page<EventsUser>> getAllEventsByAdmin(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		Page<EventsUser> response = service.getAllEventsByAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
