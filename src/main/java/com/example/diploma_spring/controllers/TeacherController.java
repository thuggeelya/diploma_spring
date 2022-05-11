package com.example.diploma_spring.controllers;

import com.example.diploma_spring.data.Teacher;
import com.example.diploma_spring.services.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ModelAttribute("teacherList")
    public List<Teacher> teacherList() {
        return teacherService.findAll()
                .stream()
                .sorted(Teacher.comparatorTeacher()).toList();
    }

    @GetMapping("/teachers")
    public String teachersPage() {
        return "teacher";
    }

    @GetMapping("/teachers/{id}")
    public String teacherPageById(@PathVariable("id") Long id, @NotNull Model model) {
        Teacher specificTeacher = teacherService.findById(id);
        model.addAttribute("specificTeacher", specificTeacher);

        List<String> specialities = new ArrayList<>();
        specificTeacher.getSpecializationList().forEach((s) -> specialities.add(s.getName()));

        String stringBuilder = specialities.stream().map(s -> s + ", ").collect(Collectors.joining());
        model.addAttribute("specialitiesWithComa", stringBuilder.toLowerCase(Locale.ROOT));
        model.addAttribute("numberOfComas", stringBuilder.split(",").length - 1);
        Logger.getLogger(this.getClass().getName()).info(specificTeacher.toString());
        return "teacher";
    }
}
