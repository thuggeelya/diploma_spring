package com.example.diploma_spring.controllers;

import com.example.diploma_spring.data.Student;
import com.example.diploma_spring.data.Student_work;
import com.example.diploma_spring.data.Teacher;
import com.example.diploma_spring.services.WorksService;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class WorksController {

    private final WorksService worksService;

    @Autowired
    public WorksController(WorksService worksService) {
        this.worksService = worksService;
    }

    @ModelAttribute("teacherList")
    public List<Teacher> teacherList() {
        return worksService.findAllTeachers()
                .stream()
                .sorted(Teacher.comparatorTeacher()).toList();
    }

    @ModelAttribute("studentList")
    public List<Student> studentList() {
        return worksService.findAllStudents()
                .stream()
                .sorted(Student.comparatorStudent()).toList();
    }

    @GetMapping("/works")
    public String worksPage(@NotNull Model model) {
        worksService.findAllWorks().keySet().forEach(s -> Logger.getLogger(this.getClass().getName()).info(s.toString()));

        val studentForWorkMap = worksService.findAllWorks().keySet().stream().toList().stream().collect(Collectors.toMap(s -> s, s -> worksService.findStudentsByWork_id(s.getMyStudentWorkKey().getWork_id()), (a, b) -> b));
        model.addAttribute("workList", worksService.findAllWorks().keySet().stream().toList())
             .addAttribute("scientificWorkList", worksService.findAllWorks().values().stream().toList())
             .addAttribute("studentForWorkMap", studentForWorkMap)
             .addAttribute("tempWork", new Student_work());

        return "student_works";
    }

    @PostMapping("/works/filter/teacher/{id}")
    public String filterTeacher(@PathVariable("id") Long id, @NotNull Model model) {
        worksService.findWorksByTeacher_id(id).keySet().forEach(s -> Logger.getLogger(this.getClass().getName()).info(s.toString()));

        val studentForWorkMap = worksService.findWorksByTeacher_id(id).keySet().stream().toList().stream().collect(Collectors.toMap(s -> s, s -> worksService.findStudentsByWork_id(s.getMyStudentWorkKey().getWork_id()), (a, b) -> b));
        model.addAttribute("workList", worksService.findWorksByTeacher_id(id).keySet().stream().toList())
             .addAttribute("scientificWorkList", worksService.findWorksByTeacher_id(id).values().stream().toList())
             .addAttribute("studentForWorkMap", studentForWorkMap)
             .addAttribute("tempWork", new Student_work());

        return "student_works";
    }

//    @PostMapping("/works/filter/student/{id}")
//    public String filterStudent(@PathVariable("id") Long id, @NotNull Model model) {
//        Map<Student_work, List<Student>> studentForWorkMap = worksService.findByStudent_id(id).keySet().stream().toList().stream().collect(Collectors.toMap(s -> s, s -> worksService.findStudentsByWork_id(s.getMyStudentWorkKey().getWork_id()), (a, b) -> b));
//        model.addAttribute("workList", worksService.findByStudent_id(id).keySet().stream().toList())
//             .addAttribute("teacherForWorkList", worksService.findByStudent_id(id).values().stream().toList())
//             .addAttribute("studentForWorkMap", studentForWorkMap)
//             .addAttribute("tempWork", new Student_work());
//
//        return "student_works";
//    }

    @PostMapping("/works/filter/title")
    public String filterTitle(@ModelAttribute("tempWork") @NotNull Student_work tempWork, @NotNull Model model) {
        worksService.findByTitle(tempWork.getTitle()).keySet().forEach(s -> Logger.getLogger(this.getClass().getName()).info(s.toString()));

        val studentForWorkMap = worksService.findByTitle(tempWork.getTitle()).keySet().stream().toList().stream().collect(Collectors.toMap(s -> s, s -> worksService.findStudentsByWork_id(s.getMyStudentWorkKey().getWork_id()), (a, b) -> b));
        model.addAttribute("workList", worksService.findByTitle(tempWork.getTitle()).keySet().stream().toList())
             .addAttribute("scientificWorkList", worksService.findByTitle(tempWork.getTitle()).values().stream().toList())
             .addAttribute("studentForWorkMap", studentForWorkMap)
             .addAttribute("tempWork", new Student_work());

        return "student_works";
    }
}