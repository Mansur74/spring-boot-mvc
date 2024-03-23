package com.mycompany.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.web.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}
