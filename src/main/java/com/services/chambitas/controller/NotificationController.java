package com.services.chambitas.controller;

import java.util.List;

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

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.dto.NotificationDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.INotificationService;

@RestController
@RequestMapping(path = { "/notification" })
public class NotificationController {

	@Autowired
	INotificationService service;
	
	// Crear notificación
	@PostMapping("/create")
	public ResponseEntity<Notification> createNotification(@RequestBody NotificationDTO request) throws GenericException {
		Notification response = service.createNotification(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@DeleteMapping("/change-status/{key}")
	public ResponseEntity<List<Notification>> changeStatus(@PathVariable("key") String username) throws GenericException {
		List<Notification> response = service.changeStatusAD(username);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	
	// Editar notificación
	@PutMapping("/edit/{key}")
	public ResponseEntity<Notification> editNotification(@PathVariable("key") Long id,@RequestBody NotificationDTO request) throws GenericException {
		Notification response = service.editNotificationById(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	// Eliminar notificación
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<Notification> deleteNotification(@PathVariable("key") Long id) throws GenericException {
		Notification response = service.deleteNotification(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	// Visualizar notificación
	@GetMapping("/find/{key}")
	public ResponseEntity<Notification> findNotificationById(@PathVariable("key") Long id) throws GenericException {
		Notification response = service.findNotificationById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	// Ver notificaciones por usuario movil
	@GetMapping("/find-by-offer-m/{key}")
	public ResponseEntity<List<Notification>> getAllNotificationsMovil(@PathVariable("key") String offerId) {
		List<Notification> response = service.getAllNotificationsByUserMovil(offerId);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	// Ver notificaciones por usuario WEB
	@GetMapping("/view-by-user-web")
	public ResponseEntity<Page<Notification>> getAllNotificationsByUser(
			@RequestParam("email") String emailDestination,
			@RequestParam(value = "title",defaultValue = "", required = false) String title,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<Notification> response = service.getAllNotificationsByUserWEB(emailDestination,title, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
	
	// Ver notificaciones por usuario ADMIN	
	@GetMapping("/view-admin")
	public ResponseEntity<Page<Notification>> getAllCommentsAdmin(@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<Notification> response = service.getAllNotificationByAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
	
}
