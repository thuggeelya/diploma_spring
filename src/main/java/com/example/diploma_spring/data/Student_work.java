package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Student_work {

    @EmbeddedId
    private MyStudentWorkKey myStudentWorkKey;

    private String title;
    private Date start_date;
    private Date completion_date;
    private Long score;
    private String leave_reason;

    @ManyToOne
    @JoinColumn(name = "type")
    private Student_work_type type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student_work that = (Student_work) o;
        return myStudentWorkKey != null && Objects.equals(myStudentWorkKey, that.myStudentWorkKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myStudentWorkKey);
    }
}
