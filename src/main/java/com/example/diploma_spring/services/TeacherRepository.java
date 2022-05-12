package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t where t.email = :email")
    Teacher findByEmail(@Param("email") String email);

    @Query("select t from Teacher t where t.teacher_id = :id")
    Teacher findFirstByTeacher_id(@Param("id") Long id);

    @Query("select t from Teacher t where t.personal_id = ?1")
    Teacher findByPersonal_id(Long personalId);

    @Query("select t from Teacher t " +
            "where upper(t.last_name) like upper(concat('%', :input, '%')) or upper(t.name) like upper(concat('%', :input, '%')) or upper(t.patronymic) like upper(concat('%', :input, '%'))")
    List<Teacher> customFindByInput(@Param("input") String input);
}
