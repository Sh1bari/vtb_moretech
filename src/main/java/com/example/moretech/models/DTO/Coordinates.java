package com.example.moretech.models.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
    private Double latitude;
    private Double longitude;
}
