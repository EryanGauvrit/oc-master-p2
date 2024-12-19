package com.p3openclassrooms.p3oc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.p3openclassrooms.p3oc.dto.RentalWithoutInclude;
import com.p3openclassrooms.p3oc.models.Rental;

public interface  RentalRepository extends JpaRepository<Rental, Long>{

    @Query("SELECT new com.p3openclassrooms.p3oc.dto.RentalWithoutInclude(r.id, r.name, r.description, r.price, r.surface, r.picture, r.owner.id, r.created_at, r.updated_at) FROM Rental r ORDER BY r.id DESC")
    List<RentalWithoutInclude> findAllByOrderByIdDesc();

    @Query("SELECT new com.p3openclassrooms.p3oc.dto.RentalWithoutInclude(r.id, r.name, r.description, r.price, r.surface, r.picture, r.owner.id, r.created_at, r.updated_at) FROM Rental r WHERE r.id = :id")
    RentalWithoutInclude findByIdWithoutInclude(Long id);
}
