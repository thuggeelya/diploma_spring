package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "student_work_type")
public class Student_work_type {

    @Id
    @Column(name="type")
    private String type;
    @Column(name="name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student_work_type that = (Student_work_type) o;
        return type != null && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
