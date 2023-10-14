package com.example.moretech.models.DTO;

import com.example.moretech.models.enums.DepartmentEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private DepartmentEnum departmentType;
}
