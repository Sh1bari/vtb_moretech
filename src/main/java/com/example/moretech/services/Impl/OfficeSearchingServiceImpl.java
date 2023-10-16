package com.example.moretech.services.Impl;

import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.OfficeFilter;
import com.example.moretech.services.OfficeSearchingService;
import com.example.moretech.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class OfficeSearchingServiceImpl implements OfficeSearchingService {
    private final OfficeRepo officeRepo;
    private final OfficeFilter officeFilter;

    @Override
    public List<Office> findInRadius(double longitude, double latitude, double radius) {

        List<Office> offices = officeRepo.findAll().stream()
                .filter(o->DistanceCalculator.calculateDistance(o.getLongitude(), o.getLatitude(), longitude, latitude) <= radius)
                .toList();
        return offices;
    }

    @Override
    public List<Office> findAllInRadiusByFilter(double longitude, double latitude, double radius,
                                                String hour, String hasRamp, String isIndividual) {
        List<Office> offices = this.findInRadius(longitude, latitude, radius);
        return officeFilter.filter(offices, hour, hasRamp, isIndividual);
    }
}
