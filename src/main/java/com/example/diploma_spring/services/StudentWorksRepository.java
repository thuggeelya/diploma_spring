package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Student_work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StudentWorksRepository extends JpaRepository<Student_work, Long> {

    @Query("select s from Student_work s where s.myStudentWorkKey.studentId = ?1")
    List<Student_work> findByStudentStudent_id(Long id);

    @Query("select s from Student_work s where upper(s.title) like upper(concat('%', :title, '%'))")
    List<Student_work> findAllByTitleContaining(@Param("title") String title);

    @Query("select s from Student_work s where s.start_date < :firstDate and s.start_date > :secondDate")
    List<Student_work> findAllByStart_dateBeforeAndStart_dateAfter(@Param("firstDate") Date firstDate, @Param("secondDate") Date secondDate);

    @Query("select s from Student_work s where s.myStudentWorkKey.work_id = ?1")
    List<Student_work> findAllByWork_Id(Long work_id);

    @Query("""
            select count(s) from Student_work s
            where s.type.name = ?1 and s.completion_date > ?2 or s.completion_date is null""")
    long countAllByType_NameAndCompletion_dateIsGreaterThanOrCompletion_dateIsNull(String name, Date date);

    @Query("select s from Student_work s where s.type.name = ?1 and s.completion_date > ?2 or s.completion_date is null")
    List<Student_work> findAllByType_NameAndCompletion_dateGreaterThanOrCompletion_dateIsNull(String name, Date date);
}