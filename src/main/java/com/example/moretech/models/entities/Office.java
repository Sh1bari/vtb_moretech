package com.example.moretech.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Double latitude;
    private Double longitude;
    private String metroStation;
    private Integer distance;

    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OpenHours> openHours;
    @OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OpenHours> openHoursIndividual;
}
