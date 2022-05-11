package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Scientific_work;
import com.example.diploma_spring.data.Student;
import com.example.diploma_spring.data.Student_work;
import com.example.diploma_spring.data.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorksService {

    private final WorksRepository worksRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public WorksService(WorksRepository worksRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.worksRepository = worksRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long id) {
        return studentRepository.findFirstByStudent_id(id);
    }

    public Map<Student_work, Scientific_work> findAllWorks() {
        return worksRepository.findAllWorks();
    }

    public Map<Student_work, Scientific_work> findWorksByTeacher_id(Long teacher_id) {
        return worksRepository.findWorksByTeacherTeacher_id(teacher_id);
    }

    public Map<Student_work, Scientific_work> findByTitle(String title) {
        return worksRepository.findByTitle(title);
    }

    public Map<Student_work, Scientific_work> findByDateInterval(Date firstDate, Date secondDate) {
        return worksRepository.findByDateInterval(firstDate, secondDate);
    }

    public List<Student> findStudentsByWork_id(Long work_id) {
        return worksRepository.findAllByWork_id(work_id)
                .stream()
                .map(w -> findStudentById(w.getMyStudentWorkKey().getStudentId())).collect(Collectors.toSet())
                .stream()
                .toList();
    }
}