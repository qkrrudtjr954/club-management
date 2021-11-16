package com.clubmanagement.service;

import com.clubmanagement.domain.School;
import com.clubmanagement.domain.Student;
import com.clubmanagement.repository.SchoolRepository;
import com.clubmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentService {
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    public StudentService(SchoolRepository schoolRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(Long schoolId) {
        return studentRepository.findAllBySchoolIdOrderByIdDesc(schoolId);
    }

    public List<Student> findByIds(List<Long> ids) {
        return studentRepository.findByIdIn(ids);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student create(Long schoolId, String name) throws EntityNotFoundException {
        School school = schoolRepository.getById(schoolId);
        Student student = Student.builder()
                .school(school)
                .name(name)
                .build();
        return studentRepository.save(student);
    }

}
