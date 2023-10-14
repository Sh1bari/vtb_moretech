package com.example.moretech.services;

import com.example.moretech.models.DTO.Coordinates;
import com.example.moretech.models.enums.TransportType;

public interface RouteDurationCalculator {
    Double calculateDuration(Coordinates start, Coordinates end, TransportType type);
}
