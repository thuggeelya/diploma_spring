package com.example.diploma_spring.controllers;

import com.example.diploma_spring.data.Student;
import com.example.diploma_spring.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("studentList")
    public List<Student> studentList() {
        return studentService.findAll()
                .stream()
                .sorted(Student.comparatorStudent()).toList();
    }

    @GetMapping("/students")
    public String studentsPage() {
        return "student";
    }

    @GetMapping("/students/{id}")
    public String studentPageById(@PathVariable("id") Long id, @NotNull Model model) {
        Student specificStudent = studentService.findById(id);
        model.addAttribute("specificStudent", specificStudent);

        Logger.getLogger(this.getClass().getName()).info(specificStudent.toString());
        return "student";
    }
}