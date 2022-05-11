package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Science_specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialization_id;
    private String name;

    @ManyToMany
    @JoinTable(name = "teacher_specialization",
            joinColumns = @JoinColumn(
                    name = "specialization_id",
                    referencedColumnName = "specialization_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "teacher_id"
            ))
    @ToString.Exclude
    private List<Teacher> teacherList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Science_specialization that = (Science_specialization) o;
        return specialization_id != null && Objects.equals(specialization_id, that.specialization_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
