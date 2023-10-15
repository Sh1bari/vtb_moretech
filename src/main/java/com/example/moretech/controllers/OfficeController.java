package com.example.moretech.controllers;

import com.example.moretech.models.DTO.TestDto;
import com.example.moretech.models.enums.DepartmentEnum;
import com.example.moretech.services.OfficeSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeSearchingService officeSearchingService;

    @GetMapping("/offices")
    public List<TestDto> getOfficesInRadius(
            @RequestParam(value = "radius", defaultValue = "10000") Double radius,
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "hasRamp", defaultValue = "N") String hasRamp,
            @RequestParam(value = "hour", required = false) String hour,
            @RequestParam(value = "isIndividual") Boolean isIndividual) {
        List<TestDto> req = new ArrayList<>();
        for (var o : officeSearchingService.findAllInRadiusByFilter(longitude, latitude, radius,
                hour, hasRamp, isIndividual)) {
            req.add(TestDto.builder()
                    .id(o.getId())
                    .latitude(o.getLatitude())
                    .longitude(o.getLongitude())
                    .departmentType(DepartmentEnum.OFFICE)
                    .build());
        }
        return req;
    }

}
