package com.example.moretech.services;

import com.example.moretech.models.DTO.OfficeDto;

import java.util.List;

public interface OfficeFilter {
    List<OfficeDto> filter(List<OfficeDto> offices, String hour, String hasRamp,
                           String isIndividual);
}
