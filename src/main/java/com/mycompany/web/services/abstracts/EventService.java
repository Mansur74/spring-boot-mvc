package com.mycompany.web.services.abstracts;

import java.util.List;

import com.mycompany.web.dtos.EventDto;

public interface EventService {
	void createEvent(Long clubId, EventDto eventDto);
	void updateEvent(EventDto eventDto);
	void deleteEvent(Long eventId);
	List<EventDto> findAllEvents();
	EventDto findEventById(Long eventId);
}
