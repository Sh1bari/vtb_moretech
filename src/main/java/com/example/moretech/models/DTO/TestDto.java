package com.example.moretech.models.DTO;

import com.example.moretech.models.entities.Office;
import com.example.moretech.models.enums.DepartmentEnum;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TestDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private DepartmentEnum departmentType;

    public static List<TestDto> mapFromListOfOffices(List<Office> of){
        List<TestDto> res = new ArrayList<>();
        for (Office o : of) {
            res.add(TestDto.builder()
                    .id(o.getId())
                    .latitude(o.getLatitude())
                    .longitude(o.getLongitude())
                    .departmentType(DepartmentEnum.OFFICE)
                    .build()
            );
        }
        return res;
    }
}
