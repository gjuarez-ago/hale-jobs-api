package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.dto.NotificationDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface INotificationService {
		
	Notification createNotification(NotificationDTO request);
	
	Notification editNotificationById(Long id, NotificationDTO request) throws GenericException;
	
	Notification deleteNotification(Long id) throws GenericException;
	
	Notification findNotificationById(Long id) throws GenericException;
	
	List<Notification> getAllNotificationsByUserMovil(String userId);
	
	Page<Notification> getAllNotificationsByUserWEB(String userId, String title, int pageNo, int pageSize);
	
	Page<Notification> getAllNotificationByAdmin(int pageNo, int pageSize);	
	
	List<Notification> changeStatusAD(String username);	
	
}
