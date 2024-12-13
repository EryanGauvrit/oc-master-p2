package com.p3openclassrooms.p3oc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.p3openclassrooms.p3oc.configuration.SpringSecurityConfig;
import com.p3openclassrooms.p3oc.models.Rental;
import com.p3openclassrooms.p3oc.services.FileService;
import com.p3openclassrooms.p3oc.services.RentalService;

import lombok.AllArgsConstructor;





@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final SpringSecurityConfig springSecurityConfig;
    private final FileService fileService;

    @GetMapping("/rentals")
    public List<Rental> getRentals() {
        return rentalService.getAll();
    }

    @GetMapping("/rentals/{id}")
    public Rental getRental(@PathVariable Long id) {
        return rentalService.getById(id);
    }
    
    @PostMapping(value = "/rentals", consumes = "multipart/form-data")
    public Map<String, String> createRental(
        @RequestPart("name") String name, 
        @RequestPart("surface") String surface, 
        @RequestPart("price") String price, 
        @RequestPart("description") String description, 
        @RequestPart("picture") MultipartFile picture,
        @RequestHeader("Authorization") String token
    ) {
        // remove Bearer
        token = token.substring(7);
        String ownerEmail = springSecurityConfig.jwtDecoder().decode(token).getSubject();

        String fileUrl = fileService.uploadFile(picture);
        
        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(Float.parseFloat(surface));
        rental.setPrice(Float.parseFloat(price));
        rental.setDescription(description);
        rental.setPicture(fileUrl);
        
        rentalService.create(ownerEmail, rental);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental created !");
        return response;
    }

    @PutMapping("/rentals/{id}")
    public Map<String, String> updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        rentalService.update(id, rental);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental updated !");
        return response;
    }
    
}
