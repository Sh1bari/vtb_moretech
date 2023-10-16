package com.example.moretech.models.DTO;

import com.example.moretech.models.entities.Office;
import com.example.moretech.models.entities.OpenHours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto {
    private Long id;

    private String salePointName;
    private String address;
    private String rko;
    private String officeType;
    private String salePointFormat;
    private String suoAvailability;
    private String hasRamp;
    private Double latitude;
    private Double longitude;
    private String metroStation;
    private Integer distance;
    private Double distanceFromPoint;

    private List<OpenHours> openHours;
    private List<OpenHours> openHoursIndividual;

    public static OfficeDto mapFromOffice(Office o, Double distanceFromPoint){
        return OfficeDto.builder()
                .id(o.getId())
                .salePointName(o.getSalePointName())
                .address(o.getAddress())
                .rko(o.getRko())
                .officeType(o.getOfficeType())
                .salePointFormat(o.getSalePointFormat())
                .suoAvailability(o.getSuoAvailability())
                .hasRamp(o.getHasRamp())
                .latitude(o.getLatitude())
                .longitude(o.getLongitude())
                .metroStation(o.getMetroStation())
                .distance(o.getDistance())
                .distanceFromPoint(distanceFromPoint)
                .openHours(o.getOpenHours())
                .openHoursIndividual(o.getOpenHoursIndividual())
                .build();
    }
}
