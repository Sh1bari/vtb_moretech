package com.example.moretech.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OpenHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String days;
    private String hours;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "offices_id")
    private Office office;

}
