package com.example.moretech.models.DTO;

import com.example.moretech.models.enums.DepartmentEnum;
import lombok.Data;

@Data
public class DepartmentWorkloadRequest {
    private Integer workload;
    private DepartmentEnum departmentType;
}
