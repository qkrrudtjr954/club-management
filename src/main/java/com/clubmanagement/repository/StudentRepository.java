package com.clubmanagement.repository;

import com.clubmanagement.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllBySchoolIdOrderByIdDesc(Long schoolId);

    List<Student> findByIdIn(List<Long> studentIds);

    @Query(value = "select s from Student s " +
            "left outer join ClubJoinInfo cji on s.id = cji.student.id " +
            "where s.school.id = :schoolId and (cji.club.id <> :clubId or cji.club.id is null)")
    List<Student> findNotJoinedIN(@Param("schoolId") Long schoolId, @Param("clubId") Long clubId);
}
