package com.example.moretech.services;

import com.example.moretech.models.DTO.OfficeDto;
import com.example.moretech.models.entities.Office;

import java.util.List;

public interface OfficeSearchingService {
    List<OfficeDto> findInRadius(double longitude, double latitude, double radius);
    List<OfficeDto> findAllInRadiusByFilter(double longitude, double latitude, double radius,
                                         String hour, String hasRamp, String isIndividual);
}
