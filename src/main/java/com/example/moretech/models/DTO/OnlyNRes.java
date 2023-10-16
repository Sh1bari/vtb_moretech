package com.example.moretech.models.DTO;

import com.example.moretech.models.enums.DepartmentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlyNRes {
    private Long id;
    private Double latitude;
    private Double longitude;
    private DepartmentEnum departmentType;
    private Double avgN;
    private Double distanceFromPoint;

    public static OnlyNRes mapFromOffice(OfficeDto o) {
        OnlyNRes res = new OnlyNRes();
        res.setId(o.getId());
        res.setLongitude(o.getLongitude());
        res.setLatitude(o.getLatitude());
        res.setDistanceFromPoint(o.getDistanceFromPoint());
        res.setDepartmentType(DepartmentEnum.OFFICE);
        return res;
    }

    public static FinallyNResDto mapToFinallyNResDto(OnlyNRes onlyNRes) {
        return FinallyNResDto.builder()
                .departmentType(DepartmentEnum.OFFICE)
                .id(onlyNRes.getId())
                .longitude(onlyNRes.getLongitude())
                .latitude(onlyNRes.getLatitude())
                .build();
    }
}
