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
public class Science_degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long degree_id;
    private String name;

    @OneToMany
    @ToString.Exclude
    @Transient
    private List<Teacher> teacherList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Science_degree that = (Science_degree) o;
        return degree_id != null && Objects.equals(degree_id, that.degree_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
