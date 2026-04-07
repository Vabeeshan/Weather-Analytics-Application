package com.weather.Weather_Analytics.Controllers;

import com.weather.Weather_Analytics.DTO.AnomalyDetection;
import com.weather.Weather_Analytics.DTO.AnomalyRequestCities;
import com.weather.Weather_Analytics.DTO.CityWeatherResult;
import com.weather.Weather_Analytics.Services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    //logger
    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    //Weather service Instances
    private final WeatherService weatherService;

    List<CityWeatherResult> resultForAnomaly = new ArrayList<>();


    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    //method to get weather details
    @GetMapping("/comfort")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getComfortWeather() {
        resultForAnomaly = weatherService.getWeatherResult();
        logger.info("Received the request");


        return ResponseEntity.ok(resultForAnomaly);
    }


}
