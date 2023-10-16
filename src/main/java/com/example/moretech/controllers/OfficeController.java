package com.example.moretech.controllers;

import com.example.moretech.models.DTO.*;
import com.example.moretech.models.entities.Office;
import com.example.moretech.services.NCounterService;
import com.example.moretech.services.OfficeSearchingService;
import com.example.moretech.services.WorkloadUtilService;
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
    private final WorkloadUtilService workloadUtilService;

    @GetMapping("/offices")
    public List<FinallyNResDto> getOfficesInRadius(
            @RequestParam(value = "radius", defaultValue = "1000") Double radius,
            @RequestParam(value = "longitude") Double longitude,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "hasRamp", defaultValue = "N") String hasRamp,
            @RequestParam(value = "hour", required = false) String hour,
            @RequestParam(value = "isIndividual", defaultValue = "") String isIndividual) {
        String startHour = getTime(hour);
        List<OfficeDto> officeList = new ArrayList<>();
        while (officeList.isEmpty()) {
            radius = radius * 2;
            officeList = officeSearchingService.findAllInRadiusByFilter(longitude, latitude, radius,
                    startHour, hasRamp, isIndividual);
        }
        NResDto r = nCounterService.calcNAvgPerRegion(officeList, hour);
        System.out.println(r.getAvgN());
        List<FinallyNResDto> res = nCounterService.getFinallyMetricForOffices(r, radius);
        return res;
    }
    private String getTime(String hour){
        if (hour == null) {
            hour = String.valueOf(LocalTime.now().getHour());
        }
        return hour;
    }

}
