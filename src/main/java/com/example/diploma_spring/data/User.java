package com.example.diploma_spring.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {

    private String email; // login
    private String password;
    private Person person;
}