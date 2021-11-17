package com.clubmanagement.service;

import com.clubmanagement.domain.Club;
import com.clubmanagement.domain.ClubJoinInfo;
import com.clubmanagement.domain.School;
import com.clubmanagement.domain.Student;
import com.clubmanagement.dto.ClubDto;
import com.clubmanagement.repository.ClubJoinInfoRepository;
import com.clubmanagement.repository.ClubRepository;
import com.clubmanagement.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    public Club findOne(Long clubId) throws EntityNotFoundException {
        return clubRepository.getById(clubId);
    }

    public List<Club> findAll(Long schoolId) {
        return clubRepository.findAllBySchoolIdOrderByIdDesc(schoolId);
    }


    public List<ClubDto> findJoinedClubs(Long studentId) {
        return clubRepository.findJoinedBy(studentId).stream()
                .map(result -> ClubDto.builder()
                        .club((Club) result[0])
                        .clubJoinInfo((ClubJoinInfo) result[1])
                        .build())
                .collect(Collectors.toList());
    }

    public List<Club> findJoinableClubs(Long schoolId, Long studentId) {
        return clubRepository.findNotJoinedClubs(schoolId, studentId);
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
        List<Long> alreadyJoinedStudentIds = club.getMemberInfo()
                .stream()
                .map(clubJoinInfo -> clubJoinInfo.getStudent().getId())
                .collect(Collectors.toList());

        students = students.stream()
                .filter(student -> !alreadyJoinedStudentIds.contains(student.getId()))
                .filter(student -> student.getJoinedClubCount() < 2)
                .collect(Collectors.toList());

        if (students.isEmpty()) {
            throw new IllegalArgumentException("No Students.");
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
