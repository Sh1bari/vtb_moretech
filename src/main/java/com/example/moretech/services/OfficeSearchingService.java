package com.example.moretech.services;

import com.example.moretech.models.entities.Office;

import java.util.List;

public interface OfficeSearchingService {
    List<Office> findInRadius(double longitude, double latitude, double radius);
}
