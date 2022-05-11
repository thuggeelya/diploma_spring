package com.example.diploma_spring.data;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MyTeacherSpecializationKey implements Serializable {

    private Long teacher_id;
    private Long specialization_id;
}