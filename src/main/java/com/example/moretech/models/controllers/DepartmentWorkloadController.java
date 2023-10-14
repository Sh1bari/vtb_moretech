package com.example.moretech.models.controllers;

import com.example.moretech.models.DTO.DepartmentWorkloadRequest;
import com.example.moretech.models.entities.DepartmentWorkload;
import com.example.moretech.repo.DepartmentWorkloadRepo;
import com.example.moretech.services.DepartmentWorkloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepartmentWorkloadController {

    private final DepartmentWorkloadService departmentWorkloadService;

    @PostMapping("/workload/{id}")
    private void saveWorkLoad(@PathVariable String id, @RequestBody DepartmentWorkloadRequest departmentWorkload){
        departmentWorkloadService.saveWorkload(departmentWorkload, Long.parseLong(id));
    }

}
