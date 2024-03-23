package com.mycompany.web.controllers;

import java.util.List;

import com.mycompany.web.services.abstracts.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.web.dtos.EventDto;
import com.mycompany.web.models.Club;
import com.mycompany.web.services.concretes.EventManager;

import jakarta.validation.Valid;

@Controller
public class EventController {

	private final EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	
	@GetMapping("/events/{clubId}/new")
	public String saveEvent(@PathVariable("clubId") Long clubId, Model model)
	{
		EventDto eventDto = new EventDto();
		model.addAttribute("clubId", clubId);
		model.addAttribute("event", eventDto);
		return "events-create";
	}
	
	@PostMapping("/events/{clubId}/new")
	public String createEvent(
			@PathVariable("clubId") Long clubId,
			@Valid
			@ModelAttribute("event") EventDto eventDto, 
			Model model,
			BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("event", eventDto);
			return "events-create";
		}

		eventService.createEvent(clubId, eventDto);
		return "redirect:/clubs/" + clubId;
	}
	
	@GetMapping("/events")
	public String listEvents(Model model)
	{
		List<EventDto> events = eventService.findAllEvents();
		model.addAttribute("events", events);
		return "events-list";
	}
	
	@GetMapping("/events/{eventId}")
	public String findEventById(@PathVariable("eventId") Long eventId, Model model)
	{
		EventDto eventDto = eventService.findEventById(eventId);
		model.addAttribute("event", eventDto);
		return "events-detail";
	}
	
	@GetMapping("events/{eventId}/edit")
	public String editEventForm(@PathVariable("eventId") Long eventId, Model model)
	{
		EventDto eventDto = eventService.findEventById(eventId);
		model.addAttribute("event", eventDto);
		return "events-edit";
	
	}
	
	@PostMapping("events/{eventId}/edit")
	public String updateEvent(
			@PathVariable("eventId") Long eventId,
			@Valid
			@ModelAttribute("event") EventDto eventDto,
			Model model,
			BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("event", eventDto);
			return "events-edit";
		}
		
		Club club = eventService.findEventById(eventId).getClub();
		eventDto.setId(eventId);
		eventDto.setClub(club);
		eventService.updateEvent(eventDto);
		return "redirect:/clubs";
	}
	
	@GetMapping("events/{eventId}/delete")
	public String deleteEvent(@PathVariable("eventId") Long eventId)
	{
		eventService.deleteEvent(eventId);
		return "redirect:/events";
	}
	

}
