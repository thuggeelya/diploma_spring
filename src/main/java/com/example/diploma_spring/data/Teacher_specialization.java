package com.example.diploma_spring.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Teacher_specialization {

    @EmbeddedId
    private MyTeacherSpecializationKey key;
}