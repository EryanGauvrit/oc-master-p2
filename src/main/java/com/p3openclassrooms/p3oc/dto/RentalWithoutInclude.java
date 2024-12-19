package com.p3openclassrooms.p3oc.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RentalWithoutInclude {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Float surface;
    private String picture;
    private Long owner_id;
    private Timestamp created_at;
    private Timestamp updated_at;
}
