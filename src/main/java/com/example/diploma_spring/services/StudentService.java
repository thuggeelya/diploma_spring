package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Student;
import com.example.diploma_spring.data.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByInput(String input) {
        return studentRepository.customFindByInput(input);
    }

    public Student findById(Long id) {
        return studentRepository.findFirstByStudent_id(id);
    }
}