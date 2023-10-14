package com.example.moretech.models.entities;

import com.example.moretech.models.enums.ServiceActivity;
import com.example.moretech.models.enums.ServiceCapability;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Service {
    @Enumerated(EnumType.STRING)
    private ServiceCapability serviceCapability;
    @Enumerated(EnumType.STRING)
    private ServiceActivity serviceActivity;
}
