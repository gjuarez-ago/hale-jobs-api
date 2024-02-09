package com.services.chambitas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.Notification;

public interface INotificationRepository extends JpaRepository<Notification, Long>{
	
   Notification findNotificationByConsecutive(String consecutive);
   
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.id = :consecutive",nativeQuery = true)
   Notification findNotificationById(@Param("consecutive") Long id); 
   
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.email_destination = :email AND n.reg_borrado = 0 ORDER BY n.reg_date_created ASC",nativeQuery = true)
   List<Notification> findNotificationByUserMovil(@Param("email") String email);
   
   @Query(value = "SELECT o FROM Notification o WHERE o.emailDestination = :email AND o.title LIKE %:title% AND regBorrado = 0 ORDER BY o.regDateCreated ASC")
   Page<Notification> findNotificationByUserWEB(@Param("email") String email,@Param("title") String title, Pageable pageable);
   
   @Query(value = "SELECT o FROM Notification o WHERE o.sendBy = :email AND o.title LIKE %:title% AND regBorrado = 0 ORDER BY o.regDateCreated ASC")
   Page<Notification> findNotificationBySendUserWEB(@Param("email") String email,@Param("title") String title, Pageable pageable);
      
   @Query(value = "SELECT n.* FROM notification AS n WHERE n.reg_borrado = 0",nativeQuery = true)
   Page<Notification> findNotificationByAdmin(Pageable pageable);
   
}
