package com.clubmanagement.service;

import com.clubmanagement.domain.School;
import com.clubmanagement.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void create(School school) {
        schoolRepository.save(school);
    }

    public List<School> leastCreatedSchools() {
        return schoolRepository.findTop10ByOrderByIdDesc();
    }


    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }

}
