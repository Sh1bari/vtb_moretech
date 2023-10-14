package com.example.moretech.services.Impl;

import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.OfficeSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeSearchingServiceImpl implements OfficeSearchingService {
    private final OfficeRepo officeRepo;

    @Override
    public List<Office> findInRadius(double longitude, double latitude, double radius) {
        Specification<Office> specification = ((root, query, criteriaBuilder) -> {
            double latitude1 = root.get("latitude")
        });
        return officeRepo.findAll()
    }
}
