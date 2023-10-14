package com.example.moretech.services;

import com.example.moretech.models.DTO.Coordinates;
import com.example.moretech.models.enums.TransportType;

public interface DistanceCalculator {
    Double calculateDistance(Coordinates start, Coordinates end, TransportType type);
}
