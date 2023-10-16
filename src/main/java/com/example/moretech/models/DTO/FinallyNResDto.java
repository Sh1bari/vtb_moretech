package com.example.moretech.models.DTO;

import com.example.moretech.models.enums.DepartmentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinallyNResDto {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private DepartmentEnum departmentType;
    private Double k;
    private Double n;
    private Double s;
}
