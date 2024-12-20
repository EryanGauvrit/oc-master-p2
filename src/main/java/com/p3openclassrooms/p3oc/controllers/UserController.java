package com.p3openclassrooms.p3oc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3openclassrooms.p3oc.dto.UserMe;
import com.p3openclassrooms.p3oc.services.UserService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("/user/{id}")
    public UserMe  user(@PathVariable Long id) {
        return userService.getById(id);
    }
}
