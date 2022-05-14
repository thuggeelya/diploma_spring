package com.example.diploma_spring.controllers;

import com.example.diploma_spring.data.*;
import com.example.diploma_spring.services.ProfileService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

//    @ModelAttribute("workMap")
//    public Map<Student_work, Scientific_work> workMap() {
//        return profileService.;
//    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(@PathVariable("id") Long id, @NotNull Model model, @NotNull HttpServletRequest request) {
        if (((User) request.getSession().getAttribute("ssouser")).getPerson().getClass().getName().equals(Student.class.getName())) {
            model.addAttribute("person", ((Student) ((User) request.getSession().getAttribute("ssouser")).getPerson()));
            model.addAttribute("workMap", profileService.findWorksByPersonId(id, Student.class.getName()));
        } else {
            model.addAttribute("person", ((Teacher) ((User) request.getSession().getAttribute("ssouser")).getPerson()));
            model.addAttribute("workMap", profileService.findWorksByPersonId(id, Teacher.class.getName()));
        }
        return "profile";
    }
}