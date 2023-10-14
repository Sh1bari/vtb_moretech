package com.example.moretech.services;

import com.example.moretech.models.DTO.DepartmentWorkloadRequest;

public interface DepartmentWorkloadService {
    void saveWorkload(DepartmentWorkloadRequest departmentWorkload, Long id);
}
