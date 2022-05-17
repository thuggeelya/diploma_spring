package com.example.diploma_spring.services;

import com.example.diploma_spring.data.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
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

    public Map<Scientific_work, Student_work> findAllWorks() {
        return worksRepository.findAllWorks();
    }

    public Map<Scientific_work, Student_work> findWorksByTeacher_id(Long teacher_id) {
        return worksRepository.findWorksByTeacherTeacher_id(teacher_id);
    }

    public Map<Scientific_work, Student_work> findWorksByStudent_id(Long student_id) {
        return worksRepository.findWorksByStudentStudent_id(student_id);
    }

    public Map<Scientific_work, Student_work> findByTitle(String title) {
        return worksRepository.findByTitle(title);
    }

    public Map<Scientific_work, Student_work> findByDateInterval(Date firstDate, Date secondDate) {
        return worksRepository.findByDateInterval(firstDate, secondDate);
    }

    public List<Student> findStudentsByWork_id(Long work_id) {
        return worksRepository.findAllByWork_id(work_id)
                .stream()
                .map(w -> findStudentById(w.getMyStudentWorkKey().getStudentId())).collect(Collectors.toSet())
                .stream()
                .toList();
    }

    public synchronized void save(@NotNull List<Student> list, Student_work w, User user) {
        list.forEach(s -> {
            w.getMyStudentWorkKey().setStudentId(s.getStudent_id());
            Scientific_work wSc = new Scientific_work();

            Logger.getLogger(WorksService.class.getName()).info(w.getMyStudentWorkKey().getWork_id().toString());

            wSc.setWork_id(w.getMyStudentWorkKey().getWork_id());
            wSc.setTitle(w.getTitle());
            wSc.setTeacher(teacherRepository.findByEmail(user.getUsername()));

            worksRepository.save(wSc, w);
        });
    }
}