package com.clubmanagement.repository;

import com.clubmanagement.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    public List<Club> findAllBySchoolIdOrderByIdDesc(Long schoolId);

    @Query(value = "select c, cji from Club c " +
            "inner join ClubJoinInfo cji on c.id = cji.club.id and cji.student.id = :studentId")
    public List<Object[]> findJoinedBy(Long studentId);
}
