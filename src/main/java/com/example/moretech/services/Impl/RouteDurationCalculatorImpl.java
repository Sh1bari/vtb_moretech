package com.example.moretech.services.Impl;

import com.example.moretech.exceptions.RouteCalculationException;
import com.example.moretech.models.DTO.Coordinates;
import com.example.moretech.models.enums.TransportType;
import com.example.moretech.services.RouteDurationCalculator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RouteDurationCalculatorImpl implements RouteDurationCalculator {

    private static final String URI_TEMPLATE =
            "http://router.project-osrm.org/route/v1/{type}/{startLon},{startLat};{endLon},{endLat}";

    /**
     * @throws RouteCalculationException если возникла ошибка на стороне сервиса osrm.org или
     * маршрут не может быть найден.
     */
    @Override
    @Cacheable("routeDurationByCoordinatesAndTransport")
    public Double calculateDuration(Coordinates start, Coordinates end, TransportType type) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            JsonNode response = restTemplate.getForObject(URI_TEMPLATE, JsonNode.class,
                    type,
                    start.getLongitude(), start.getLatitude(),
                    end.getLongitude(), end.getLatitude());
            return response.get("routes").get(0).get("duration").asDouble();
        } catch (RuntimeException e) {
            throw new RouteCalculationException("Route from " + start + " to " + end + " can't be found.");
        }
    }
}
