package com.example.moretech.controllers;

import com.example.moretech.models.entities.Office;
import com.example.moretech.services.OfficeSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeSearchingService officeSearchingService;

    @GetMapping("/offices")
    public List<Office> getOfficesInRadius(
            @RequestParam(value = "radius", defaultValue = "10000") Double radius,
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "hasRamp", defaultValue = "N") String hasRamp,
            @RequestParam(value = "hour", required = false) String hour,
            @RequestParam(value = "isIndividual") Boolean isIndividual) {
        return officeSearchingService.findAllInRadiusByFilter(longitude, latitude, radius,
                hour, hasRamp, isIndividual);
    }

}
