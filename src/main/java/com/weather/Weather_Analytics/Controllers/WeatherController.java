package com.weather.Weather_Analytics.Controllers;

import com.weather.Weather_Analytics.DTO.CityWeatherResult;
import com.weather.Weather_Analytics.Services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/weather")
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(WeatherController.class);

   private final WeatherService weatherService;

   public WeatherController(WeatherService weatherService){
       this.weatherService = weatherService;
   }

    @GetMapping("/comfort")
    public ResponseEntity<?> getComfortWeather(){

       logger.info("Received the request");

       return ResponseEntity.ok(weatherService.getWeatherResult());
    }


}
