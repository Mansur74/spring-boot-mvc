package com.mycompany.web.services.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.web.dtos.ClubDto;
import com.mycompany.web.mappers.ClubMapper;
import com.mycompany.web.models.Club;
import com.mycompany.web.models.UserEntity;
import com.mycompany.web.repositories.ClubRepository;
import com.mycompany.web.repositories.UserRepository;
import com.mycompany.web.security.SecurityUtil;
import com.mycompany.web.services.abstracts.ClubService;

@Service
public class ClubManager implements ClubService {
	
	private ClubRepository clubRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ClubManager(ClubRepository clubRepository, UserRepository userRepository) {
		this.clubRepository = clubRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<ClubDto> findAllClubs() {
		
		List<Club> clubs = clubRepository.findAll();
		return clubs.stream().map(club -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}
	
	@Override
	public Club saveClub(ClubDto clubDto) {
		String username = SecurityUtil.getSessionUser();
		UserEntity user = userRepository.findByUsername(username);
		Club club = ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		return clubRepository.save(club);
		
	}

	@Override
	public ClubDto findClubById(Long clubId) {
		Club club = clubRepository.findById(clubId).get();
		return ClubMapper.mapToClubDto(club);
	}
	
	@Override
	public void updateClub(ClubDto clubDto) {
		String username = SecurityUtil.getSessionUser();
		UserEntity user = userRepository.findByUsername(username);
		Club club = ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		clubRepository.save(club);
	}

	@Override
	public void delete(Long clubId) {
		clubRepository.deleteById(clubId);
	}

	@Override
	public List<ClubDto> searchClubs(String query) {

		List<Club> clubs = clubRepository.searchClubs(query);
		return clubs.stream().map(club -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}


	

}
