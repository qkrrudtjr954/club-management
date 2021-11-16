package com.clubmanagement.service;

import com.clubmanagement.domain.Club;
import com.clubmanagement.domain.ClubJoinInfo;
import com.clubmanagement.domain.School;
import com.clubmanagement.domain.Student;
import com.clubmanagement.repository.ClubJoinInfoRepository;
import com.clubmanagement.repository.ClubRepository;
import com.clubmanagement.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubJoinInfoRepository clubJoinInfoRepository;
    private final SchoolRepository schoolRepository;

    public ClubService(ClubRepository clubRepository, ClubJoinInfoRepository clubJoinInfoRepository, SchoolRepository schoolRepository) {
        this.clubRepository = clubRepository;
        this.clubJoinInfoRepository = clubJoinInfoRepository;
        this.schoolRepository = schoolRepository;
    }

    public Club getOne(Long clubId) throws EntityNotFoundException {
        return clubRepository.getById(clubId);
    }

    public List<Club> findAll(Long schoolId) {
        return clubRepository.findAllBySchoolIdOrderByIdDesc(schoolId);
    }

    public Club create(Long schoolId, String name) {
        School school = schoolRepository.getById(schoolId);

        Club club = Club.builder()
                .school(school)
                .name(name)
                .build();

        return clubRepository.save(club);
    }

    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    public void join(Club club, List<Student> students) {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("students is empty");
        }

        for (Student student : students) {
            ClubJoinInfo joinInfo = ClubJoinInfo.builder()
                    .club(club)
                    .student(student)
                    .build();

            clubJoinInfoRepository.save(joinInfo);
        }
    }
}
