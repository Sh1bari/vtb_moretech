package com.example.moretech.models.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class OpenHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String days;
    private String hours;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "offices_id")
    private Office office;
}
