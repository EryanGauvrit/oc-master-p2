package com.p3openclassrooms.p3oc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.p3openclassrooms.p3oc.dto.UserMe;
import com.p3openclassrooms.p3oc.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new com.p3openclassrooms.p3oc.dto.UserMe(u.id, u.name, u.email, u.created_at, u.updated_at) FROM User u WHERE u.email = ?1")
    UserMe getUserMeByEmail(String email);
    
    User findByEmail(String email);
}
