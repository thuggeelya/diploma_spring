package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Scientific_work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScientificWorksRepository extends JpaRepository<Scientific_work, Long> {

    @Query("select s from Scientific_work s where s.work_id = :work_id")
    Scientific_work findByWork_id(@Param("work_id") Long work_id);

    @Query("select s from Scientific_work s where s.teacher.teacher_id = :teacher_id")
    List<Scientific_work> findByTeacherTeacher_id(@Param("teacher_id") Long teacher_id);
}