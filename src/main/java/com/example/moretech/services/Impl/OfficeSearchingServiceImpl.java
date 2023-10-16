package com.example.moretech.services.Impl;

import com.example.moretech.models.DTO.OfficeDto;
import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.OfficeFilter;
import com.example.moretech.services.OfficeSearchingService;
import com.example.moretech.services.WorkloadUtilService;
import com.example.moretech.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeSearchingServiceImpl implements OfficeSearchingService {
    private final OfficeRepo officeRepo;
    private final OfficeFilter officeFilter;
    private final WorkloadUtilService workloadUtilService;

    @Override
    public List<OfficeDto> findInRadius(double longitude, double latitude, double radius) {
        List<OfficeDto> res = new ArrayList<>();
        List<Office> offices = officeRepo.findAll();
        for (Office o : offices) {
            double dis = DistanceCalculator.calculateDistance(o.getLongitude(), o.getLatitude(), longitude, latitude);
            if (dis <= radius) {
                res.add(OfficeDto.mapFromOffice(o, dis));
            }
        }
        return res;
    }


    @Override
    public List<OfficeDto> findAllInRadiusByFilter(double longitude, double latitude, double radius,
                                                   String hour, String hasRamp, String isIndividual) {
        List<OfficeDto> offices = this.findInRadius(longitude, latitude, radius);
        return officeFilter.filter(offices, hour, hasRamp, isIndividual);
    }
}
