package com.example.moretech.services.Impl;

import com.example.moretech.models.entities.Atm;
import com.example.moretech.models.entities.DepartmentWorkload;
import com.example.moretech.models.entities.Office;
import com.example.moretech.models.enums.DepartmentEnum;
import com.example.moretech.repo.AtmRepo;
import com.example.moretech.repo.DepartmentWorkloadRepo;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.CreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreateServiceImpl implements CreateService {
    private final DepartmentWorkloadRepo departmentWorkloadRepo;
    private final OfficeRepo officeRepo;
    private final AtmRepo atmRepo;

    @Override
    public void create() {
        Iterable<Office> officeIterable = officeRepo.findAll();
        for (Office office : officeIterable) {
            DepartmentWorkload dw = new DepartmentWorkload();
            DepartmentWorkload.builder()
                    .departmentId(office.getId())
                    .departmentType(DepartmentEnum.OFFICE)
                    .workload(generateRandomNumber(0,10))
                    .time(LocalDateTime.now())//время
                    .build();
            departmentWorkloadRepo.save(dw);
        }
        Iterable<Atm> atmIterable = atmRepo.findAll();
        for (Atm atm : atmIterable) {
            DepartmentWorkload dw = new DepartmentWorkload();
            DepartmentWorkload.builder()
                    .departmentId(atm.getId())
                    .departmentType(DepartmentEnum.ATM)
                    .workload(generateRandomNumber(0,10))
                    .time(LocalDateTime.now())//время
                    .build();
            departmentWorkloadRepo.save(dw);
        }
    }

    private static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Минимальное значение должно быть меньше максимального значения.");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
