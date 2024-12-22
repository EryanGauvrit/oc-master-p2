package com.p3openclassrooms.p3oc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p3openclassrooms.p3oc.models.Rental;

public interface  RentalRepository extends JpaRepository<Rental, Long>{

}
