package com.clubmanagement.repository;

import com.clubmanagement.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    public List<School> findTop10ByOrderByIdDesc();
}
