package com.weather.Weather_Analytics.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnomalyRequestCities {
    private List<AnomalyDetection> cities;
}
