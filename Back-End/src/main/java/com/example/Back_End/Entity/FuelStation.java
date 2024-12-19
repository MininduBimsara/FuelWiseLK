package com.example.Back_End.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FuelStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generation of primary key
    @EqualsAndHashCode.Include // Primary key for equality
    private int stationID;

    @Column(nullable = false) // Ensure not null
    @ToString.Include
    private String stationName;

    @Column(nullable = false) // Ensure not null
    @ToString.Include
    private String stationLocation;

    @Column(nullable = false) // Ensure not null
    @ToString.Include
    private String stationContact;

    @OneToMany(mappedBy = "fuelStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuelTransaction> fuelTransactions; // Bidirectional one-to-many with FuelTransaction
}