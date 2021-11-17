package com.clubmanagement.repository;

import com.clubmanagement.domain.ClubJoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubJoinInfoRepository extends JpaRepository<ClubJoinInfo, Long> {
    @Modifying
    @Query("delete from ClubJoinInfo c where c.club.id = :clubId and c.student.id in :studentIds")
    public void deleteAllByClubIdAndStudentIdIn(@Param("clubId") Long clubId, @Param("studentIds") List<Long> studentIds);

}
