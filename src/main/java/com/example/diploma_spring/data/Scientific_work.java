package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Scientific_work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long work_id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Scientific_work that = (Scientific_work) o;
        return work_id != null && Objects.equals(work_id, that.work_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
