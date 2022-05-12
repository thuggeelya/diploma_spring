package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Person;
import com.example.diploma_spring.data.User;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LoginService implements UserDetailsService {

//    private final String DB_URL = Environment.getProperties().getProperty("db.url");
//    private final String DB_NAME = Environment.getProperties().getProperty("db.name");
//    private final String DB_USER = Environment.getProperties().getProperty("db.user");
//    private final String DB_PWD = Environment.getProperties().getProperty("db.password");

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final RoleRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public LoginService(StudentRepository studentRepository, TeacherRepository teacherRepository,
                        UserRepository userRepository, RoleRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Nullable
    public User getRegisteredUser(@NotNull User loginForm) {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Сбой аутентификации. Повторите попытку");
        }

        return user;
    }
}