package com.mycompany.web.services.abstracts;

import java.util.List;

import com.mycompany.web.dtos.ClubDto;
import com.mycompany.web.models.Club;

public interface ClubService {
    List<ClubDto> findAllClubs();
    List<ClubDto> searchClubs(String query);
    Club saveClub(ClubDto clubDto);
    void updateClub(ClubDto clubDto);
    void delete(Long clubId);
    ClubDto findClubById(Long clubId);
}
