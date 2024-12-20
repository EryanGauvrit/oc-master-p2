package com.p3openclassrooms.p3oc.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserMe {
    private Long id;
    private String name;
    private String email;
    private Timestamp created_at;
    private Timestamp updated_at;

}
