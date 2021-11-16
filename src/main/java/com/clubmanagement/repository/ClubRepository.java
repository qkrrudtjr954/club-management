package com.clubmanagement.repository;

import com.clubmanagement.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    public List<Club> findAllBySchoolIdOrderByIdDesc(Long schoolId);
}
