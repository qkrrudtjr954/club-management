package com.clubmanagement.repository;

import com.clubmanagement.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllBySchoolIdOrderByIdDesc(Long schoolId);
    List<Student> findByIdIn(List<Long> studentIds);

}
