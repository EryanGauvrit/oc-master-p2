package com.p3openclassrooms.p3oc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {
    
    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

}
