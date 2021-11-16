package com.clubmanagement.controller;

import com.clubmanagement.domain.Student;
import com.clubmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/schools/{schoolId}/students")
    public String indexStudents(@PathVariable Long schoolId, Model model) {
        List<Student> students = studentService.findAll(schoolId);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("students", students);
        return "student/index";
    }

    @GetMapping("/schools/{schoolId}/students/create")
    public String createStudent(@PathVariable Long schoolId, Model model) {
        model.addAttribute("schoolId", schoolId);
        return "student/create";
    }

    @PostMapping("/schools/{schoolId}/students")
    public RedirectView create(@PathVariable Long schoolId, @RequestParam String name) {
        studentService.create(schoolId, name);
        Path path = Paths.get("/", "schools", String.valueOf(schoolId), "students");
        return new RedirectView(path.toString());
    }

    @DeleteMapping("/schools/{schoolId}/students")
    public RedirectView delete(@PathVariable Long schoolId, @RequestParam Long id) {
        studentService.delete(id);
        Path path = Paths.get("/", "schools", String.valueOf(schoolId), "students");
        return new RedirectView(path.toString());
    }
}
