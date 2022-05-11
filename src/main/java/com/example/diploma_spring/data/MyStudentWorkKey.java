package com.example.diploma_spring.data;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Embeddable
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MyStudentWorkKey implements Serializable, Comparable<MyStudentWorkKey> {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "work_id")
    private Long work_id;

    @Override
    public int compareTo(@NotNull MyStudentWorkKey o) {
        return this.getWork_id().compareTo(o.getWork_id());
    }

//    @OneToMany(mappedBy = "student_id")
//    @ToString.Exclude
//    private List<Student> studentList;
}