package com.services.chambitas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.services.chambitas.domain.EventsUser;

public interface IEventUserRepository extends JpaRepository<EventsUser, Long>{
	
	EventsUser findEventsUserById(Long id);
	
	@Query(value = "SELECT * FROM events_user AS e WHERE e.user_recruiter_id = :user", nativeQuery = true)
	Page<EventsUser> getAllEventsByUserRecruiter(@Param("user") Long user,  Pageable pageable);

	@Query(value = "SELECT * FROM events_user AS e WHERE e.user_guest_id = :user", nativeQuery = true)
	Page<EventsUser> getAllEventsByUserGuest(@Param("user") Long user,  Pageable pageable);
	
}
