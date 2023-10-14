package com.example.moretech.services.Impl;

import com.example.moretech.models.entities.Office;
import com.example.moretech.models.entities.OpenHours;
import com.example.moretech.services.OfficeFilter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OfficeFilterImpl implements OfficeFilter {
    @Override
    public List<Office> filter(List<Office> offices, String hour, String hasRamp, Boolean isIndividual) {
        if (isIndividual) {
            return offices.parallelStream()
                    .filter(office -> {
                        if (office.getHasRamp().equals(hasRamp)) {
                            Optional<OpenHours> hours = office.getOpenHoursIndividual().stream()
                                    .filter(openHours -> openHours.getDays().equals(getFormattedDay()))
                                    .findFirst();

                            if (hours.isPresent()) {
                                return isTimeInRange(hours.get().getHours(), LocalTime.now());
                            }
                        }
                        return false;
                    }).toList();
        } else {
            return offices.parallelStream()
                    .filter(office -> {
                        if (office.getHasRamp().equals(hasRamp)) {
                            Optional<OpenHours> hours = office.getOpenHours().stream()
                                    .filter(openHours -> openHours.getDays().equals(getFormattedDay()))
                                    .findFirst();

                            if (hours.isPresent()) {
                                return isTimeInRange(hours.get().getHours(), LocalTime.now());
                            }
                        }
                        return false;
                    }).toList();
        }
    }

    private String getFormattedDay() {
        return switch (LocalDateTime.now().getDayOfWeek()) {
            case MONDAY -> "пн";
            case TUESDAY -> "вт";
            case WEDNESDAY -> "ср";
            case THURSDAY -> "чт";
            case FRIDAY -> "пт";
            case SATURDAY -> "сб";
            case SUNDAY -> "вс";
        };
    }

    private boolean isTimeInRange(String timeRange, LocalTime time) {
        if (!timeRange.equals("выходной")) {
            String[] parts = timeRange.split("-");
            LocalTime start = LocalTime.parse(parts[0]);
            LocalTime end = LocalTime.parse(parts[1]);

            return time.isAfter(start) && time.isBefore(end);
        }

        return false;
    }
}
