package com.mycompany.web.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.web.dtos.EventDto;
import com.mycompany.web.mappers.EventMapper;
import com.mycompany.web.models.Club;
import com.mycompany.web.models.Event;
import com.mycompany.web.repositories.ClubRepository;
import com.mycompany.web.repositories.EventRepository;
import com.mycompany.web.services.abstracts.EventService;

@Service
public class EventManager implements EventService {

	EventRepository eventRepository;
	ClubRepository clubRepository;
	
	@Autowired
	public EventManager(EventRepository eventRepository, ClubRepository clubRepository) {
		this.eventRepository = eventRepository;
		this.clubRepository = clubRepository;
	}


	@Override
	public void createEvent(Long clubId, EventDto eventDto) {	
		Club club = clubRepository.findById(clubId).get();
		Event event = EventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
	}
	
	@Override
	public void updateEvent(EventDto eventDto) {
		Event event = EventMapper.mapToEvent(eventDto);
		eventRepository.save(event);	
	}


	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events = eventRepository.findAll();	
		return events.stream().map(event -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
	}


	@Override
	public EventDto findEventById(Long eventId) {
		Event event = eventRepository.findById(eventId).get();
		return EventMapper.mapToEventDto(event);
	}


	@Override
	public void deleteEvent(Long eventId) {	
		eventRepository.deleteById(eventId);
	}



	
	
	
	
}
