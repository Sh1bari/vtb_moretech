package com.example.moretech.services.Impl;

import com.example.moretech.models.DTO.OfficeDto;
import com.example.moretech.models.entities.Office;
import com.example.moretech.models.entities.OpenHours;
import com.example.moretech.services.OfficeFilter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfficeFilterImpl implements OfficeFilter {
    /**
     *
     * @param offices
     * @param hour - хочет получить инф
     * @param hasRamp - есть пандус
     * @param isIndividual - физЛицо?
     * @return
     */
    @Override
    public List<OfficeDto> filter(List<OfficeDto> offices, String hour, String hasRamp, String isIndividual) {
        List<OfficeDto> officeList = new ArrayList<>();

        offices.forEach(o->{
            if(isIndConv(o.getHasRamp(), hasRamp)) {    //Есть рампа
                if(individualCheck(o, isIndividual)){      //ип или юр
                    if(workInHour(o, hour)){
                        officeList.add(o);
                    }
                }
            }
        });
        return officeList;
    }
    private boolean workInHour(OfficeDto office, String hour){
        String dayNow = getFormattedDay();
        for (OpenHours o : office.getOpenHours()) {
            if(dayNow.equals(o.getDays())) {
                if (isTimeInRange(o.getHours(), LocalTime.of(Integer.parseInt(hour), 0))) {
                    return true;
                }
            }
        }
        for (OpenHours o : office.getOpenHoursIndividual()) {
            if(dayNow.equals(o.getDays())) {
                if (isTimeInRange(o.getHours(), LocalTime.of(Integer.parseInt(hour), 0))) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean individualCheck(OfficeDto office, String isIndividual){
        if(isIndividual.equals("")){
            return true;
        } else if (office.getOpenHours().get(0).getDays().equals("Не обслуживает ЮЛ") && isIndividual.equals("N")) {
            return false;
        } else if (office.getOpenHoursIndividual().get(0).getDays().equals("Не обслуживает ИП") && isIndividual.equals("Y")) {
            return false;
        }else return true;
    }

    private boolean isIndConv(String str, String hasRamp){
        if(str == null){
            return false;
        }else if(hasRamp.equals("N")){
            return true;
        }else if(hasRamp.isEmpty()){
            return true;
        }else return str.equals("Y") && hasRamp.equals("Y");
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
