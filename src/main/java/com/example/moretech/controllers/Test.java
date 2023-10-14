package com.example.moretech.controllers;

import com.example.moretech.models.DTO.DepartmentWorkloadRequest;
import com.example.moretech.models.DTO.TestDto;
import com.example.moretech.models.entities.Office;
import com.example.moretech.models.entities.OpenHours;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.repo.OpenHoursRepo;
import com.example.moretech.services.DepartmentWorkloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class Test {

    private final DepartmentWorkloadService departmentWorkloadService;
    private final OfficeRepo officeRepo;

    @GetMapping("/getAll")
    private List<TestDto> a() {
        List<TestDto> req = new ArrayList<>();
        for (var o : officeRepo.findAll()) {
            req.add(TestDto.builder()
                    .id(o.getId())
                    .latitude(o.getLatitude())
                    .longitude(o.getLongitude())
                    .build());
        }
        return req;
    }
    private final OpenHoursRepo openHoursRepo;

    @PostMapping("/initDBOffice")
    private void init(@RequestBody List<Office> offices) {
        for (Office office : offices) {
            officeRepo.save(office);
            for (OpenHours o : office.getOpenHours()) {
                subDays(office, o);
            }
            for (OpenHours o : office.getOpenHoursIndividual()) {
                subDays(office, o);
            }
        }
    }

    private void subDays(Office office, OpenHours o) {
        switch (o.getDays()) {
            case "пн-вт" -> {
                save("пн", o, office);
                save("вт", o, office);
            }
            case "пн-ср" -> {
                save("пн", o, office);
                save("вт", o, office);
                save("ср", o, office);
            }
            case "пн-чт" -> {
                save("пн", o, office);
                save("вт", o, office);
                save("ср", o, office);
                save("чт", o, office);
            }
            case "пн-пт" -> {
                save("пн", o, office);
                save("вт", o, office);
                save("ср", o, office);
                save("чт", o, office);
                save("пт", o, office);
            }
            case "пн-сб" -> {
                save("пн", o, office);
                save("вт", o, office);
                save("ср", o, office);
                save("чт", o, office);
                save("пт", o, office);
                save("сб", o, office);
            }
            case "пн-вс" -> {
                save("пн", o, office);
                save("вт", o, office);
                save("ср", o, office);
                save("чт", o, office);
                save("пт", o, office);
                save("сб", o, office);
                save("вс", o, office);
            }
            case "сб,вс" -> {
                save("сб", o, office);
                save("вс", o, office);
            }
            case "в" -> {
                save("вс", o, office);
            }
            default -> save(o.getDays(), o, office);
        }
    }

    private void save(String day, OpenHours o, Office office) {
        o.setDays(day);
        o.setOffice(office);
        openHoursRepo.save(o);
    }
}
