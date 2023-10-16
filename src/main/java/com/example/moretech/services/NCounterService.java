package com.example.moretech.services;

import com.example.moretech.models.DTO.FinallyNResDto;
import com.example.moretech.models.DTO.NResDto;
import com.example.moretech.models.DTO.OfficeDto;
import com.example.moretech.models.DTO.OnlyNRes;

import java.util.List;

public interface NCounterService {
    NResDto calcNAvgPerRegion(List<OfficeDto> officeList, String hour);

    OnlyNRes calcAvgNForOffice(OfficeDto office, String hour);

    List<FinallyNResDto> getFinallyMetricForOffices(NResDto o, Double radius);

}
