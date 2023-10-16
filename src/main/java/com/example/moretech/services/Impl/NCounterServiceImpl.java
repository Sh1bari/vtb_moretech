package com.example.moretech.services.Impl;

import com.example.moretech.models.DTO.*;
import com.example.moretech.models.entities.Office;
import com.example.moretech.repo.DepartmentWorkloadRepo;
import com.example.moretech.repo.OfficeRepo;
import com.example.moretech.services.NCounterService;
import com.example.moretech.services.WorkloadUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NCounterServiceImpl implements NCounterService {
    private final WorkloadUtilService workloadUtilService;
    private final OfficeRepo officeRepo;
    private final DepartmentWorkloadRepo departmentWorkloadRepo;

    @Override
    public NResDto calcNAvgPerRegion(List<OfficeDto> officeList, String hour) {
        NResDto res = new NResDto();
        res.setOnlyNRes(new ArrayList<>());
        double avg = 0.0;
        for (OfficeDto o : officeList) {
            OnlyNRes r = calcAvgNForOffice(o, hour);
            avg = (avg + r.getAvgN()) / 2;
            res.getOnlyNRes().add(r);
        }
        res.setAvgN(avg);
        return res;
    }

    @Override
    public OnlyNRes calcAvgNForOffice(OfficeDto office, String hour) {
        OnlyNRes r = new OnlyNRes();
        double avg = 0.0;
        LocalDateTime search = LocalDateTime.now().withHour(Integer.parseInt(hour)).withMinute(0);
        LocalDateTime dateTimeMonthAgo = search.minusMonths(1);
        while (dateTimeMonthAgo.isBefore(search)) {
            List<IdWorkloadDto> doubles = departmentWorkloadRepo.findDepartmentWorkloadsByIdAndTimeRange(office.getId(),
                    dateTimeMonthAgo.withMinute(0),
                    dateTimeMonthAgo.plusHours(1).withMinute(0));
            if (!doubles.isEmpty() && doubles.get(0).getWorkload() != null) {
                avg = (avg + doubles.get(0).getWorkload()) / 2;
                Office of = officeRepo.findById(doubles.get(0).getDepartmentId()).get();
                r = OnlyNRes.mapFromOffice(OfficeDto.mapFromOffice(of, office.getDistanceFromPoint()));
            }
            dateTimeMonthAgo = dateTimeMonthAgo.plusWeeks(1);
        }
        r.setAvgN(avg);
        return r;
    }

    private Double calcAvg(List<Double> d) {
        double avg = 0;
        for (Double o : d) {
            avg = (avg + o) / 2;
        }
        return avg;
    }

    private List<Double> extractAvgNFromOnlyNResList(List<OnlyNRes> onlyNResList) {
        return onlyNResList.stream()
                .map(OnlyNRes::getAvgN)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinallyNResDto> getFinallyMetricForOffices(NResDto o, Double radius) {
        List<FinallyNResDto> res = new ArrayList<>();
        for (OnlyNRes o1 : o.getOnlyNRes()) {
            FinallyNResDto dto = OnlyNRes.mapToFinallyNResDto(o1);
            double n = workloadUtilService.calculateWorkloadMetric(o1.getAvgN(), o.getAvgN());
            double s = workloadUtilService.linearFunction(o1.getDistanceFromPoint(), radius);
            dto.setN(n);
            dto.setS(s);
            dto.setK(workloadUtilService.calculatePredictedK(n, s, 1.0, 1.0));
            res.add(dto);
        }
        return res;
    }


}
