package com.example.moretech.controllers;

import com.example.moretech.models.DTO.TestDto;
import com.example.moretech.models.entities.Office;
import com.example.moretech.services.NCounterService;
import com.example.moretech.services.OfficeSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeSearchingService officeSearchingService;
    private final NCounterService nCounterService;

    @GetMapping("/offices")
    public List<TestDto> getOfficesInRadius(
            @RequestParam(value = "radius", defaultValue = "1000") Double radius,
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "hasRamp", defaultValue = "N") String hasRamp,
            @RequestParam(value = "hour", required = false) String hour,
            @RequestParam(value = "isIndividual", required = false) String isIndividual) {
        String startHour = getTime(hour);
        List<Office> officeList = officeSearchingService.findAllInRadiusByFilter(longitude, latitude, radius,
                startHour, hasRamp, isIndividual);
        /*double a = nCounterService.calcNAvgPerRegion(officeList, hour);

        System.out.println(a);*/
        List<TestDto> res = TestDto.mapFromListOfOffices(officeList);
        return res;
    }
    private String getTime(String hour){
        if (hour == null) {
            hour = String.valueOf(LocalTime.now().getHour());
        }
        return hour;
    }

}
