package com.example.diploma_spring.services;

import com.example.diploma_spring.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT nextval(pg_get_serial_sequence('user', 'id'))", nativeQuery = true)
    Long getNextId();
}