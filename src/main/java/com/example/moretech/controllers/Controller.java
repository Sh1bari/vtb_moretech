package com.example.moretech.controllers;

import com.example.moretech.models.entities.Atm;
import com.example.moretech.models.entities.Office;
import com.example.moretech.models.entities.OpenHours;
import com.example.moretech.repo.AtmRepo;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.repo.OpenHoursRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final OfficeRepo officeRepo;
    private final OpenHoursRepo openHoursRepo;
    private final AtmRepo atmRepo;

    @PostMapping("/initDBOffice")
    private void initOffice(@RequestBody List<Office> offices) {
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
    @PostMapping("/initDBAtm")
    private void initAtm(@RequestBody List<Atm> Atms) {
        atmRepo.saveAll(Atms);
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
