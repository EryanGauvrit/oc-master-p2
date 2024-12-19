package com.p3openclassrooms.p3oc.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.p3openclassrooms.p3oc.models.Message;
import com.p3openclassrooms.p3oc.models.Rental;
import com.p3openclassrooms.p3oc.models.User;
import com.p3openclassrooms.p3oc.repositories.MessageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final RentalService rentalService;

    public Message create(String content, Long userId, Long rentalId) {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.from(now);

        User user = userService.findById(userId);
        Rental rental = rentalService.findById(rentalId);

        Message message = new Message();
        message.setMessage(content);
        message.setCreated_at(timestamp);
        message.setUpdated_at(timestamp);
        message.setUser(user);
        message.setRental(rental);
        return messageRepository.save(message);
    }
}
