package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.email = :email")
    Student findByEmail(@Param("email") String email);

    @Query("select s from Student s where s.personal_id = ?1")
    Student findByPersonal_id(Long personalId);

    @Query("select s from Student s " +
            "where upper(s.last_name) like upper(concat('%', :input, '%')) or upper(s.name) like upper(concat('%', :input, '%')) or upper(s.patronymic) like upper(concat('%', :input, '%'))")
    List<Student> customFindByInput(@Param("input") String input);

    @Query("select s from Student s where s.student_id = :id")
    Student findFirstByStudent_id(@Param("id") Long id);
}