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
public class Academic_position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acad_pos_id;
    private String name;

    @OneToMany(mappedBy = "teacher_id")
    @ToString.Exclude
    private List<Teacher> teacherList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Academic_position that = (Academic_position) o;
        return acad_pos_id != null && Objects.equals(acad_pos_id, that.acad_pos_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
