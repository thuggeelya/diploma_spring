package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacher_id;
    private String last_name;
    private String name;
    private String patronymic;
    private String email;
    @Column(unique=true)
    private Long personal_id;

    @ManyToOne
    @JoinColumn(name = "acad_pos_id")
    private Academic_position academicPosition;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Science_degree degree;

    @ManyToMany
    @JoinTable(
            name = "teacher_specialization",
            joinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "teacher_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "specialization_id",
                    referencedColumnName = "specialization_id"
            )
    )
    @ToString.Exclude
    private List<Science_specialization> specializationList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return teacher_id != null && Objects.equals(teacher_id, teacher.teacher_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static Comparator<Teacher> comparatorTeacher() {
        return Comparator.comparing(Teacher::getLast_name).thenComparing(Teacher::getName).thenComparing(Teacher::getPatronymic);
    }
}
