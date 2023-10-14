package com.example.moretech.services;


public interface WorkloadUtilService {
    Double calculateWorkloadMetric(Double x, Double avg);
    Double calculateCurrentK(Double predictedN, Double currentN, Double distance, Double w1, Double w2);
    Double calculatePredictedK(Double predictedN, Double distance, Double w1, Double w2);
}
