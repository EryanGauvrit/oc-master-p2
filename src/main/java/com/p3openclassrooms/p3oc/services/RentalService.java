package com.p3openclassrooms.p3oc.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.p3openclassrooms.p3oc.models.Rental;
import com.p3openclassrooms.p3oc.repositories.RentalRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserService userService;

    public Rental create(String ownerEmail, Rental rental) {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.from(now);
        rental.setOwner(userService.getByEmail(ownerEmail));
        rental.setCreated_at(timestamp);
        rental.setUpdated_at(timestamp);
        return rentalRepository.save(rental);
    }

    public Rental findById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found"));
    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental update(Long id, Rental rental) {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.from(now);
        Rental rentalToUpdate = rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        rentalToUpdate.setUpdated_at(timestamp);
        rentalToUpdate.setName(rental.getName());
        rentalToUpdate.setDescription(rental.getDescription());
        rentalToUpdate.setPrice(rental.getPrice());
        rentalToUpdate.setSurface(rental.getSurface());
        return rentalRepository.save(rentalToUpdate);
    }
}
