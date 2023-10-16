package com.example.moretech.services;

import com.example.moretech.models.entities.Office;

import java.util.List;

public interface OfficeFilter {
    List<Office> filter(List<Office> offices, String hour, String hasRamp,
                        String isIndividual);
}
