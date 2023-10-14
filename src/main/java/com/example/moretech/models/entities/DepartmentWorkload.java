package com.example.moretech.models.entities;

import com.example.moretech.models.enums.DepartmentEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
