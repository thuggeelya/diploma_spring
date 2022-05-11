package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public List<Teacher> findByInput(String input) {
        return teacherRepository.customFindByInput(input);
    }

    public Teacher findById(Long id) {
        return teacherRepository.findFirstByTeacher_id(id);
    }
}
