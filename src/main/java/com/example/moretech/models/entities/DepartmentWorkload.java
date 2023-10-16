package com.example.moretech.models.entities;

import com.example.moretech.models.enums.DepartmentEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class DepartmentWorkload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long departmentId;

    @Enumerated(EnumType.STRING)
    private DepartmentEnum departmentType;
    private Integer workload;

    @Basic
    private LocalDateTime time;
}
