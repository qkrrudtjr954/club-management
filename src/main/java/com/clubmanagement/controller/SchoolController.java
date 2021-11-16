package com.clubmanagement.controller;

import com.clubmanagement.domain.School;
import com.clubmanagement.repository.SchoolRepository;
import com.clubmanagement.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping("/schools")
    public String index(Model model) {
        List<School> schools = schoolService.leastCreatedSchools();
        model.addAttribute("schools", schools);
        return "school/index";
    }

    @RequestMapping("/schools/create")
    public String createSchool() {
        return "school/create";
    }

    @RequestMapping(value = "/schools", method = RequestMethod.POST)
    public RedirectView create(@RequestParam String name) {
        School school = School.builder()
                .name(name)
                .build();
        schoolService.create(school);
        return new RedirectView("/schools");
    }

    @RequestMapping(value = "/schools", method = RequestMethod.DELETE)
    public RedirectView delete(@RequestParam Long id) {
        schoolService.delete(id);
        return new RedirectView("/schools");
    }
}
