package com.p3openclassrooms.p3oc.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.p3openclassrooms.p3oc.configuration.SpringSecurityConfig;
import com.p3openclassrooms.p3oc.dto.UserMe;
import com.p3openclassrooms.p3oc.models.User;
import com.p3openclassrooms.p3oc.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private  final SpringSecurityConfig springSecurityConfig;

    public User create(User user) {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.from(now);
        String hashedPassword = springSecurityConfig.passwordEncoder().encode(user.getPassword());
        user.setCreated_at(timestamp);
        user.setUpdated_at(timestamp);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserMe getMeByEmail(String email) {
        return userRepository.getUserMeByEmail(email);
    }

    public UserMe getById(Long id) {
        return userRepository.getUserById(id);
    }

    public User update(Long id, User user) {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.from(now);
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userToUpdate.setUpdated_at(timestamp);
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(userToUpdate);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
