package com.example.moretech.controllers;

import com.example.moretech.models.DTO.DepartmentWorkloadRequest;
import com.example.moretech.models.DTO.TestDto;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.DepartmentWorkloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Test {

    private final DepartmentWorkloadService departmentWorkloadService;
    private final OfficeRepo officeRepo;

    @PostMapping("/workload/{id}")
    private void saveWorkLoad(@PathVariable String id, @RequestBody DepartmentWorkloadRequest departmentWorkload){
        System.out.println("прием");
        departmentWorkloadService.saveWorkload(departmentWorkload, Long.parseLong(id));
    }
    @GetMapping("/getAll")
    private List<TestDto> a(){
        List<TestDto> req = new ArrayList<>();
        for (var o : officeRepo.findAll()) {
            req.add(TestDto.builder()
                    .id(o.getId())
                    .latitude(o.getLatitude())
                    .longitude(o.getLongitude())
                    .build());
        }
        return req;
    }

}
