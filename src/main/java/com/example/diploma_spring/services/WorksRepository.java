package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Scientific_work;
import com.example.diploma_spring.data.Student_work;
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

    public Map<Scientific_work, Student_work> findAllWorks() {
        val studentWorks = studentWorksRepository.findAll();
        val scientific_works = scientificWorksRepository.findAll();
        studentWorks.sort(Comparator.comparing(Student_work::getMyStudentWorkKey));
        scientific_works.sort(Comparator.comparing(Scientific_work::getWork_id));
        Map<Scientific_work, Student_work> map = new HashMap<>();
        IntStream.range(0, studentWorks.size()).forEachOrdered(i -> map.put(scientific_works.get(i), studentWorks.get(i)));
        return map;
    }

    public Map<Scientific_work, Student_work> findWorksByTeacherTeacher_id(Long id) {
        List<Student_work> studentWorks = new ArrayList<>();
        val scientific_works = scientificWorksRepository.findByTeacherTeacher_id(id);
        scientific_works.sort(Comparator.comparing(Scientific_work::getWork_id));
        scientific_works.forEach(s -> studentWorks.addAll(studentWorksRepository.findAllByWork_Id(s.getWork_id())));
        Map<Scientific_work, Student_work> map = new HashMap<>();
        IntStream.range(0, studentWorks.size()).forEachOrdered(i -> map.put(scientificWorksRepository.findByWork_id(studentWorks.get(i).getMyStudentWorkKey().getWork_id()), studentWorks.get(i)));
        return map;
    }

    public Map<Scientific_work, Student_work> findWorksByStudentStudent_id(Long id) {
        val studentWorks = studentWorksRepository.findByStudentStudent_id(id);
        return getStudent_workScientific_workMap(studentWorks);
    }

    public Map<Scientific_work, Student_work> findByTitle(String t) {
        val studentWorks = studentWorksRepository.findAllByTitleContaining(t);
        return getStudent_workScientific_workMap(studentWorks);
    }

    public Map<Scientific_work, Student_work> findByDateInterval(Date d1, Date d2) {
        val studentWorks = studentWorksRepository.findAllByStart_dateBeforeAndStart_dateAfter(d1, d2);
        return getStudent_workScientific_workMap(studentWorks);
    }

    @NotNull
    private Map<Scientific_work, Student_work> getStudent_workScientific_workMap(@NotNull List<Student_work> studentWorks) {
        List<Scientific_work> scientific_works = new ArrayList<>();
        studentWorks.sort(Comparator.comparing(Student_work::getMyStudentWorkKey));
        studentWorks.forEach(s -> scientific_works.add(scientificWorksRepository.findByWork_id(s.getMyStudentWorkKey().getWork_id())));
        return IntStream
                .range(0, studentWorks.size())
                .boxed()
                .collect(Collectors.toMap(scientific_works::get, studentWorks::get, (a, b) -> b));
    }

    public Map<Scientific_work, Student_work> findByWork_Id(Long id) {
        Map<Scientific_work, Student_work> map = new TreeMap<>(Comparator.comparing(Scientific_work::getWork_id));
        map.put(scientificWorksRepository.findByWork_id(id), studentWorksRepository.findAllByWork_Id(id).get(0));
        return map;
    }

    public List<Student_work> findAllByWork_id(Long work_id) {
        return studentWorksRepository.findAllByWork_Id(work_id);
    }

    public void save(Scientific_work w1, Student_work w2) {
        scientificWorksRepository.save(w1);
        studentWorksRepository.save(w2);
    }
}