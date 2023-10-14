package com.example.moretech.models.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String address;
    private Double latitude;
    private Double longitude;
    private Boolean allDay;

    @Embedded
    private Services services;
}
