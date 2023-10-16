package com.example.moretech.services;

import com.example.moretech.models.entities.Office;

import java.util.List;

public interface NCounterService {
    Double calcNAvgPerRegion(List<Office> officeList, String hour);

}
