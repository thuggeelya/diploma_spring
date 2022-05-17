package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "student")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private String last_name;
    private String name;
    private String patronymic;
    private String email;
    @Column(unique=true)
    private Long personal_id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return student_id != null && Objects.equals(student_id, student.student_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static Comparator<Student> comparatorStudent() {
        return Comparator.comparing(Student::getLast_name).thenComparing(Student::getName).thenComparing(Student::getPatronymic);
    }
}