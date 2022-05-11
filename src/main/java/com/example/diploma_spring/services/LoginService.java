package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Person;
import com.example.diploma_spring.data.Student;
import com.example.diploma_spring.data.Teacher;
import com.example.diploma_spring.data.User;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

//    private final String DB_URL = Environment.getProperties().getProperty("db.url");
//    private final String DB_NAME = Environment.getProperties().getProperty("db.name");
//    private final String DB_USER = Environment.getProperties().getProperty("db.user");
//    private final String DB_PWD = Environment.getProperties().getProperty("db.password");

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public LoginService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Nullable
    private User getRegisteredUser(@NotNull User loginForm) {
        Person person;
        person = studentRepository.findByEmail(loginForm.getEmail());

        if (person == null) person = teacherRepository.findByEmail(loginForm.getEmail());

        if (person != null) {
            loginForm.setPerson(person);
            return loginForm;
        }

        return null;
    }

    public boolean authenticate(@NotNull User loginForm) {
        val user = getRegisteredUser(loginForm);
        if (user == null) {
            return false;
        }

        return loginForm.getPassword().equals(user.getPassword());
    }
}