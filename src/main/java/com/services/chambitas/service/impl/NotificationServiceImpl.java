package com.services.chambitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.dto.NotificationDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.INotificationRepository;
import com.services.chambitas.service.INotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements INotificationService{

	@Autowired
	private INotificationRepository notificationRepository;
	
//	@Autowired 
//	private IUserRepository userRepository;
//	
//	@Autowired
//	private IOfferRepository offerRepository;
//	
	@Override
	public Notification createNotification(NotificationDTO request) {
		
		Notification element =  new Notification();
		
		element.setConsecutive(generateConsecutive());
		element.setContent(request.getContent());
		element.setTitle(request.getTitle());
		element.setEmailDestination(request.getEmailDestination());
		element.setTypeAD(request.getTypeAD());
		notificationRepository.save(element);
	
		return element;
	}

	@Override
	public Notification editNotificationById(Long id, NotificationDTO request) throws GenericException {
		
		Notification element = existNotification(id);
		
		element.setTitle(request.getTitle());
		element.setContent(request.getContent());
		element.setEmailDestination(request.getEmailDestination());
		element.setTypeAD("A");
		
		notificationRepository.save(element);
		
		return element;
		
	}

	@Override
	public Notification deleteNotification(Long id) throws GenericException {
		
		Notification element = existNotification(id);
		element.setRegBorrado(1);
		return element;
	}

	@Override
	public Notification findNotificationById(Long id) throws GenericException {
		Notification element = existNotification(id);
		return element;
	}

	@Override
	public List<Notification> getAllNotificationsByUserMovil(String userId) {
	   List<Notification> list = notificationRepository.findNotificationByUserMovil(userId);
		return list;
	}

	@Override
	public Page<Notification> getAllNotificationsByUserWEB(String email,String title, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Notification> response = notificationRepository.findNotificationByUserWEB(email, title, pageable);
		return response;
	}

	@Override
	public Page<Notification> getAllNotificationByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Notification> response = notificationRepository.findNotificationByAdmin(pageable);
		return response;
	}
	
	// Private methods
	private Notification existNotification(Long id) throws GenericException {
		
		Notification element = notificationRepository.findNotificationById(id);
		if(element == null) {
			throw new GenericException("No se encontro la notificación");
		}
		
		return element;
	}
	

	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = notificationRepository.count();
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

	@Override
	public List<Notification> changeStatusAD(String username) {
		List<Notification> list = notificationRepository.findNotificationByUserMovil(username);

		for (Notification notification : list) {
			notification.setStatus(2);			
		}
		
		notificationRepository.saveAll(list);
		return list;
	}
	
	
	
	

}
