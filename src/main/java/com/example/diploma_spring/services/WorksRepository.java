package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Scientific_work;
import com.example.diploma_spring.data.Student;
import com.example.diploma_spring.data.Student_work;
import com.example.diploma_spring.data.Teacher;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class WorksRepository {

    private final StudentWorksRepository studentWorksRepository;
    private final ScientificWorksRepository scientificWorksRepository;

    @Autowired
    public WorksRepository(StudentWorksRepository studentWorksRepository,
                           ScientificWorksRepository scientificWorksRepository) {
        this.studentWorksRepository = studentWorksRepository;
        this.scientificWorksRepository = scientificWorksRepository;
    }

    public Map<Student_work, Scientific_work> findAllWorks() {
        val studentWorks = studentWorksRepository.findAll();
        val scientific_works = scientificWorksRepository.findAll();
        studentWorks.sort(Comparator.comparing(Student_work::getMyStudentWorkKey));
        scientific_works.sort(Comparator.comparing(Scientific_work::getWork_id));
        Map<Student_work, Scientific_work> map = new HashMap<>();
        IntStream.range(0, studentWorks.size()).forEachOrdered(i -> map.put(studentWorks.get(i), scientific_works.get(i)));
        return map;
    }

    public Map<Student_work, Scientific_work> findWorksByTeacherTeacher_id(Long id) {
        List<Student_work> studentWorks = new ArrayList<>();
        val scientific_works = scientificWorksRepository.findByTeacherTeacher_id(id);
        scientific_works.sort(Comparator.comparing(Scientific_work::getWork_id));
        scientific_works.forEach(s -> studentWorks.add(studentWorksRepository.findByWork_Id(s.getWork_id()).get()));
        Map<Student_work, Scientific_work> map = new HashMap<>();
        IntStream.range(0, studentWorks.size()).forEachOrdered(i -> map.put(studentWorks.get(i), scientific_works.get(i)));
        return map;
    }

    public Map<Student_work, Scientific_work> findByTitle(String t) {
        val studentWorks = studentWorksRepository.findAllByTitleContaining(t);
        return getStudent_workScientific_workMap(studentWorks);
    }

    public Map<Student_work, Scientific_work> findByDateInterval(Date d1, Date d2) {
        val studentWorks = studentWorksRepository.findAllByStart_dateBeforeAndStart_dateAfter(d1, d2);
        return getStudent_workScientific_workMap(studentWorks);
    }

    @NotNull
    private Map<Student_work, Scientific_work> getStudent_workScientific_workMap(List<Student_work> studentWorks) {
        List<Scientific_work> scientific_works = new ArrayList<>();
        studentWorks.sort(Comparator.comparing(Student_work::getMyStudentWorkKey));
        studentWorks.forEach(s -> scientific_works.add(scientificWorksRepository.findByWork_id(s.getMyStudentWorkKey().getWork_id())));
        Map<Student_work, Scientific_work> map = new HashMap<>();
        IntStream.range(0, studentWorks.size()).forEachOrdered(i -> map.put(studentWorks.get(i), scientific_works.get(i)));
        return map;
    }

    public Map<Student_work, Scientific_work> findByWork_Id(Long id) {
        Map<Student_work, Scientific_work> map = new TreeMap<>(Comparator.comparing(Student_work::getMyStudentWorkKey));
        map.put(studentWorksRepository.findByWork_Id(id).get(), scientificWorksRepository.findByWork_id(id));
        return map;
    }

    public List<Student_work> findAllByWork_id(Long work_id) {
        return studentWorksRepository.findAllByWork_Id(work_id);
    }
}