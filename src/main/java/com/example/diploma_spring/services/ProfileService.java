package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Scientific_work;
import com.example.diploma_spring.data.Student_work;
import com.example.diploma_spring.data.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProfileService {

    private final StudentWorksRepository studentWorksRepository;
    private final ScientificWorksRepository scientificWorksRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public ProfileService(StudentWorksRepository studentWorksRepository, ScientificWorksRepository scientificWorksRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentWorksRepository = studentWorksRepository;
        this.scientificWorksRepository = scientificWorksRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public Map<Student_work, Scientific_work> findWorksByPersonId(Long id, String className) {
        List<Student_work> studentWorks = new ArrayList<>();
        List<Scientific_work> scientific_works;
        Map<Student_work, Scientific_work> map;

        if (className.equals(Teacher.class.getName())) {
            scientific_works = scientificWorksRepository.findByTeacherTeacher_id(id);
            scientific_works.sort(Comparator.comparing(Scientific_work::getWork_id));
            List<Student_work> finalStudentWorks = studentWorks;
            scientific_works.forEach(s -> finalStudentWorks.addAll(studentWorksRepository.findAllByWork_Id(s.getWork_id())));
            List<Scientific_work> finalScientific_works = scientific_works;
            map = new HashMap<>();
            IntStream.range(0, studentWorks.size()).forEachOrdered(i -> map.put(finalStudentWorks.get(i), finalScientific_works.get(i)));
        } else {
            studentWorks = studentWorksRepository.findAll();
            studentWorks.sort(Comparator.comparing(Student_work::getMyStudentWorkKey));
            map = studentWorks
                    .stream()
                    .filter(s -> Objects.equals(s.getMyStudentWorkKey().getStudentId(), id))
                    .collect(Collectors.toMap(s -> s, s -> scientificWorksRepository.findByWork_id(s.getMyStudentWorkKey().getWork_id()), (a, b) -> b));
        }

        return map;
    }
}