package com.example.moretech.services.Impl;

import com.example.moretech.services.WorkloadUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkloadUtilServiceImpl implements WorkloadUtilService {
    @Override
    public Double calculateWorkloadMetric(Double x, Double avg) {
        return Math.tanh(0.2 * x - (avg / 5)) / 2 + 0.5;
    }
}
