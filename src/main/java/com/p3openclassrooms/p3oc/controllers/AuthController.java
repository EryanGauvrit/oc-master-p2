package com.p3openclassrooms.p3oc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3openclassrooms.p3oc.configuration.SpringSecurityConfig;
import com.p3openclassrooms.p3oc.models.User;
import com.p3openclassrooms.p3oc.services.JWTService;
import com.p3openclassrooms.p3oc.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final JWTService jwtService;
    private final UserService userService;
    private final SpringSecurityConfig springSecurityConfig;

    @Operation(summary = "User login")
    @PostMapping("/login")  
    public Map<String, String> getToken(@RequestBody User user) {
        User userFound = userService.getByEmail(user.getEmail());
        if (userFound == null) {
            throw new BadCredentialsException("User not found");
        }
        // password check
        if (!springSecurityConfig.passwordEncoder().matches(user.getPassword(), userFound.getPassword())) {
            throw new BadCredentialsException("Password not match");
        }
        String token = jwtService.generateToken(userFound.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        User userCreated = userService.create(user);
        if (userCreated == null) {
            throw new RuntimeException("User not created");
        }
        String token = jwtService.generateToken(userCreated.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
    
    @GetMapping("/me")
    public User getUserAuth(@RequestHeader("Authorization") String token) {
        // remove Bearer
        token = token.substring(7);
        String email = springSecurityConfig.jwtDecoder().decode(token).getSubject();
        return userService.getByEmail(email);
    }
    
}
