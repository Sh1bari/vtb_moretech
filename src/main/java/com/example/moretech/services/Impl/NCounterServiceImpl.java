package com.example.moretech.services.Impl;

import com.example.moretech.models.entities.DepartmentWorkload;
import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.DepartmentWorkloadRepo;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.NCounterService;
import com.example.moretech.services.WorkloadUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NCounterServiceImpl implements NCounterService {
    private final WorkloadUtilService workloadUtilService;
    private final OfficeRepo officeRepo;
    private final DepartmentWorkloadRepo departmentWorkloadRepo;

    @Override
    public Double calcNAvgPerRegion(List<Office> officeList, String hour) {
        double avg = 0.0;
        for (Office o : officeList) {
            LocalDateTime search = LocalDateTime.now().withHour(Integer.parseInt(hour)).withMinute(0);
            LocalDateTime dateTimeMonthAgo = search.minusMonths(1);
            while (dateTimeMonthAgo.isBefore(search)){
                List<Double> doubles = departmentWorkloadRepo.findDepartmentWorkloadsByIdAndTimeRange(o.getId(),
                        dateTimeMonthAgo.withMinute(0),
                        dateTimeMonthAgo.plusHours(1).withMinute(0));
                if(!doubles.isEmpty()){
                    avg = (avg + doubles.get(0))/2;
                }
                dateTimeMonthAgo = dateTimeMonthAgo.plusWeeks(1);
            }
        }
        return avg;
    }
}
