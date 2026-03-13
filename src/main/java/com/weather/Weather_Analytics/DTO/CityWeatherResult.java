/*
    This class represents the final weather analytics result for each city.
    So this class acts as a response object returned by your API.

            cities.json
             ↓
            OpenWeatherMap API
             ↓
            WeatherService
             ↓
            ComfortIndexService
             ↓
            CityWeatherResult
             ↓
            Controller → Frontend
 */


package com.weather.Weather_Analytics.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityWeatherResult {
    private String CityName;
    private Double ComfortScore;
    private String Rank;
    private String Decsription;
    private Double Temperature;

}
