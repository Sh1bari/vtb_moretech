package com.example.moretech.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NResDto {
    private List<OnlyNRes> onlyNRes;
    private double avgN;
}
