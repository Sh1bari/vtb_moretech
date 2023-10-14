package com.example.moretech.services.Impl;

import com.example.moretech.services.WorkloadUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkloadUtilServiceImpl implements WorkloadUtilService {
    /**
     * Calculates a workload metric, based on <code>x</code> - average queue at the given working unit
     * and <code>avg</code> - average queue at the given region.
     * <p>A calculated value is within (0;1).</p>
     */
    @Override
    public Double calculateWorkloadMetric(Double x, Double avg) {
        return Math.tanh(0.2 * x - (avg / 5)) / 2 + 0.5;
    }

    /**
     * Calculates a current workload coefficient at the given working unit.
     */
    @Override
    public Double calculateCurrentK(Double predictedN, Double currentN, Double distance, Double w1, Double w2) {
        return (predictedN + currentN) / 2 * w1 + distance * w2;
    }

    /**
     * Calculates a predicted workload coefficient at the given working unit.
     */
    @Override
    public Double calculatePredictedK(Double predictedN, Double distance, Double w1, Double w2) {
        return predictedN * w1 + distance * w2;
    }
}
