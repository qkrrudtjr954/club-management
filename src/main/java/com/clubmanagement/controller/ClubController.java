package com.clubmanagement.controller;

import com.clubmanagement.domain.Club;
import com.clubmanagement.domain.Student;
import com.clubmanagement.service.ClubService;
import com.clubmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ClubController {
    private final StudentService studentService;
    private final ClubService clubService;

    public ClubController(StudentService studentService, ClubService clubService) {
        this.studentService = studentService;
        this.clubService = clubService;
    }

    @GetMapping("/schools/{schoolId}/clubs")
    public String indexClubs(@PathVariable Long schoolId, Model model) {
        List<Club> clubs = clubService.findAll(schoolId);
        model.addAttribute("clubs", clubs);
        return "club/index";
    }

    @GetMapping("/schools/{schoolId}/clubs/{clubId}")
    public String detailClub(@PathVariable Long schoolId, @PathVariable Long clubId, Model model) {
        Club club = clubService.findOne(clubId);
        model.addAttribute("club", club);
        return "club/detail";
    }

    @GetMapping("/schools/{schoolId}/clubs/create")
    public String createClub(@PathVariable Long schoolId, Model model) {
        model.addAttribute("schoolId", schoolId);
        return "club/create";
    }

    @GetMapping("/schools/{schoolId}/clubs/{clubId}/students")
    public String addStudents(@PathVariable Long schoolId, @PathVariable Long clubId, Model model) {
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("clubId", clubId);

        Club club = clubService.findOne(clubId);

        // 해당 동아리에 가입하지 않은 학생 목록
        List<Student> students = studentService.findNotJoinedIn(club);
        model.addAttribute("students", students);

        return "club/addStudents";
    }

    @PostMapping("/schools/{schoolId}/clubs")
    public RedirectView create(@PathVariable Long schoolId, @RequestParam String name) {
        clubService.create(schoolId, name);

        Path path = Paths.get("/", "schools", schoolId.toString(), "clubs");
        return new RedirectView(path.toString());
    }

    @DeleteMapping("/schools/{schoolId}/clubs")
    public RedirectView delete(@PathVariable Long schoolId, @RequestParam Long id) {
        clubService.delete(id);

        Path path = Paths.get("/", "schools", schoolId.toString(), "clubs");
        return new RedirectView(path.toString());
    }

    @PostMapping("/schools/{schoolId}/clubs/{clubId}/students")
    public RedirectView addStudents(@PathVariable Long schoolId, @PathVariable Long clubId, @RequestParam List<Long> studentIds) {
        Club club = clubService.findOne(clubId);
        List<Student> students = studentService.findByIds(studentIds);

        clubService.join(club, students);

        Path path = Paths.get("/", "schools", schoolId.toString(), "clubs", clubId.toString());
        return new RedirectView(path.toString());
    }
}
