package com.example.moretech.services.Impl;

import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.OfficeFilter;
import com.example.moretech.services.OfficeSearchingService;
import com.example.moretech.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class OfficeSearchingServiceImpl implements OfficeSearchingService {
    private final OfficeRepo officeRepo;
    private final OfficeFilter officeFilter;

    @Override
    public List<Office> findInRadius(double longitude, double latitude, double radius) {
        Predicate<Office> predicate = office -> DistanceCalculator.calculateDistance(
                office.getLongitude(), office.getLatitude(),
                longitude, latitude) <= radius;

        return officeRepo.findAll().parallelStream()
                .filter(predicate).toList();
    }

    @Override
    public List<Office> findAllInRadiusByFilter(double longitude, double latitude, double radius,
                                                String hour, String hasRamp, Boolean isIndividual) {

        return officeFilter.filter(
                this.findInRadius(longitude, latitude, radius),
                hour, hasRamp, isIndividual);
    }
}
