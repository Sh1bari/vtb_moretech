package com.example.moretech.controllers;

import com.example.moretech.models.DTO.FinallyNResDto;
import com.example.moretech.models.DTO.NResDto;
import com.example.moretech.models.DTO.OfficeDto;
import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.NCounterService;
import com.example.moretech.services.OfficeSearchingService;
import com.example.moretech.services.WorkloadUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    private final OfficeRepo officeRepo;

    @GetMapping("/office/{id}")
    private Office getOffice(@PathVariable String id) {
        return officeRepo.findById(Long.parseLong(id)).get();
    }

    /**
     *
     * @param radius - радиус поиска
     * @param longitude
     * @param latitude
     * @param hasRamp N Y
     * @param hour 0-24
     * @param isIndividual N Y
     * @return k- итоговая оценка (меньше - лучше)
     * @return n- оценка нагрузки (меньше - лучше)
     * @return s- оценка расстояния (меньше - лучше)
     */
    @GetMapping("/offices")
    public List<FinallyNResDto> getOfficesInRadius(
            @RequestParam(value = "radius", defaultValue = "3000") Double radius,
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

    private String getTime(String hour) {
        if (hour == null) {
            hour = String.valueOf(LocalTime.now().getHour());
        }
        return hour;
    }

}
