package com.example.moretech.models.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestDto {
    private Long id;
    private Double latitude;
    private Double longitude;
}
