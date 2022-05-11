package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "teacher_id")
    @ToString.Exclude
    private List<Teacher> teacherList = new ArrayList<>();

    @OneToMany(mappedBy = "speciality_id")
    @ToString.Exclude
    private List<Speciality> specialityList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Department that = (Department) o;
        return department_id != null && Objects.equals(department_id, that.department_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
