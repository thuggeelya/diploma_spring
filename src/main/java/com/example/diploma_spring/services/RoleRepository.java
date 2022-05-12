package com.example.diploma_spring.services;

import com.example.diploma_spring.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}