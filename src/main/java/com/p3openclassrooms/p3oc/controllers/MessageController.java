package com.p3openclassrooms.p3oc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p3openclassrooms.p3oc.dto.MessageRequestBody;
import com.p3openclassrooms.p3oc.services.MessageService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api")
@AllArgsConstructor 
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/messages")
    public Map<String, String> sendMessage(@RequestBody MessageRequestBody message) {
        messageService.create(message.getMessage(), message.getUser_id(), message.getRental_id());
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Message send with success");
        return response;
    }
    
}   
