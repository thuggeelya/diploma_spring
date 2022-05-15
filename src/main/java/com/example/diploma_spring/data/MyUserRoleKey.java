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
public class MyUserRoleKey implements Serializable {

    private String email;
    private Long role_id;
}