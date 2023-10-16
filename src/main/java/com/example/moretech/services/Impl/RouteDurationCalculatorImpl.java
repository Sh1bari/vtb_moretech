package com.example.moretech.services.Impl;

import com.example.moretech.exceptions.RouteCalculationException;
import com.example.moretech.models.DTO.Coordinates;
import com.example.moretech.models.enums.TransportType;
import com.example.moretech.services.RouteDurationCalculator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RouteDurationCalculatorImpl implements RouteDurationCalculator {
    @Value("${geoapify-key}")
    private String GEOAPIFY_KEY;
    private static final String URI_TEMPLATE =
            "https://api.geoapify.com/v1/routing?waypoints={startLat},{startLon}|{endLat},{endLon}&mode={type}&apiKey={key}";

    /**
     * @throws RouteCalculationException если возникла ошибка на стороне сервиса geoapify или
     *                                   маршрут не может быть найден.
     */
    @Override
    @Cacheable("routeDurationByCoordinatesAndTransport")
    public Double calculateDuration(Coordinates start, Coordinates end, TransportType type) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            JsonNode response = restTemplate.getForObject(URI_TEMPLATE, JsonNode.class,
                    start.getLatitude(), start.getLongitude(),
                    end.getLatitude(), end.getLongitude(),
                    type.toString().toLowerCase(), GEOAPIFY_KEY);
            return response.get("features").get(0).get("properties").get("time").asDouble();
        } catch (RuntimeException e) {
            throw new RouteCalculationException("Route from " + start + " to " + end + " can't be found.");
        }
    }
}
