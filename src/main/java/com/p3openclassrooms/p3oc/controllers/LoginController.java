package com.p3openclassrooms.p3oc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;

import com.p3openclassrooms.p3oc.services.JWTService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    public JWTService jwtService;

    public LoginController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")  
    public Map<String, String> getToken(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
    
}
