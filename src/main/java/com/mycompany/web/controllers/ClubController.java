package com.mycompany.web.controllers;
import com.mycompany.web.dtos.ClubDto;
import com.mycompany.web.models.Club;
import com.mycompany.web.services.abstracts.ClubService;
import com.mycompany.web.services.concretes.ClubManager;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ClubController {
	private final ClubService clubService;
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}
	
	
	@GetMapping("/clubs")
	public String listClubs(Model model)
	{
		List<ClubDto> clubs = clubService.findAllClubs();
		model.addAttribute("clubs", clubs);
		return "clubs-list";
		
	}
	
	@GetMapping("/clubs/{clubId}")
	public String clubDetail(@PathVariable("clubId") Long clubId, Model model)
	{
		ClubDto clubDto = clubService.findClubById(clubId);
		model.addAttribute("club", clubDto);
		return "clubs-detail";		
	}
	
	@GetMapping("/clubs/search")
	public String clubSearch(@RequestParam(value="query") String query, Model model)
	{
		List<ClubDto> clubs = clubService.searchClubs(query);
		model.addAttribute("clubs", clubs);
		return "clubs-list";
	}
	
	
	@GetMapping("/clubs/new")
	public String createClubForm(Model model)
	{
		Club club = new Club();
		model.addAttribute("club", club);
		return "clubs-create";
		
	}
	
	@PostMapping("/clubs/new")
	public String saveClub(
			@Valid 
			@ModelAttribute("club") ClubDto clubDto, 
			Model model, 
			BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("club", clubDto);
			return "clubs-create";
		}
		clubService.saveClub(clubDto);
		return "redirect:/clubs";
		
	}
	
	@GetMapping("/clubs/{clubId}/delete")
	public String deleteClub(@PathVariable("clubId") Long clubId) 
	{
		clubService.delete(clubId);
		return "redirect:/clubs";
	}
	
	@GetMapping("/clubs/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") Long clubId, Model model)
	{
		ClubDto clubDto = clubService.findClubById(clubId);
		model.addAttribute("club", clubDto);
		return "clubs-edit";
	}
	
	@PostMapping("clubs/{clubId}/edit")
	public String updateClub(
			@PathVariable("clubId")Long clubId,
			@Valid
			@ModelAttribute("club") ClubDto clubDto,
			Model model,
			BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("club", clubDto);
			return "clubs-edit";
		}
		clubDto.setId(clubId);
		clubService.updateClub(clubDto);
		return "redirect:/clubs";
	}
	
}
