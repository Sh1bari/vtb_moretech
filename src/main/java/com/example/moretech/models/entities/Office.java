package com.example.moretech.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String salePointName;
    private String address;
    private String rko;
    private String officeType;
    private String salePointFormat;
    private String suoAvailability;
    private String hasRamp;
    private double latitude;
    private double longitude;
    private String metroStation;
    private int distance;

    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OpenHours> openHours;
    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OpenHours> openHoursIndividual;
}
