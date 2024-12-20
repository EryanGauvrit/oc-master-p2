package com.p3openclassrooms.p3oc.models;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"rentals"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Timestamp created_at;
    private Timestamp updated_at;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messages;
}
