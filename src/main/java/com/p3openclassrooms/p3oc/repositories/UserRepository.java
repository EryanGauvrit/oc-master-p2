package com.p3openclassrooms.p3oc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p3openclassrooms.p3oc.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
}
