package com.example.diploma_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class MainService {

    private final StudentRepository studentRepository;
    private final StudentWorksRepository studentWorksRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public MainService(StudentRepository studentRepository, StudentWorksRepository studentWorksRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.studentWorksRepository = studentWorksRepository;
        this.teacherRepository = teacherRepository;
    }

    public long countAllByTypeName(String name) {
        return studentWorksRepository.countAllByType_NameAndCompletion_dateIsGreaterThanOrCompletion_dateIsNull(name, Date.from(Instant.now()));
    }

    public long countActualByTypeNameAndCourse(String name, int course) {
        return studentWorksRepository.findAllByType_NameAndCompletion_dateGreaterThanOrCompletion_dateIsNull(name, Date.from(Instant.now()))
                .stream()
                .filter(s -> studentRepository.findFirstByStudent_id(s.getMyStudentWorkKey().getStudentId())
                        .getGroup()
                        .getCourse()
                        .equals(String.valueOf(course)))
                .count();
    }
}