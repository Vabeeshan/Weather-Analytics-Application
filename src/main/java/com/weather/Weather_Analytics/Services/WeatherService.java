/*
    It connects all the components together:
    JsonReaderUtil → reads cities from cities.json
    OpenWeatherClient → calls the OpenWeather API
    ComfortIndexService → calculates the comfort level

    The WeatherService is responsible for:
        Reading city data from JSON
        Fetching live weather data from OpenWeather API
        Calculating comfort index
        Preparing final weather results
        Returning data to the controller
 */


package com.weather.Weather_Analytics.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.weather.Weather_Analytics.Client.OpenWeatherClient;
import com.weather.Weather_Analytics.DTO.CityWeatherResult;
import com.weather.Weather_Analytics.Exception.WeatherExceptions;
import com.weather.Weather_Analytics.Models.City;
import com.weather.Weather_Analytics.Utils.JsonReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final OpenWeatherClient openWeatherClient;
    private final JsonReaderUtil jsonReaderUtil;
    private final ComfortIndexService comfortIndexService;

    public WeatherService(OpenWeatherClient openWeatherClient,
                          JsonReaderUtil jsonReaderUtil,
                          ComfortIndexService comfortIndexService) {
        this.openWeatherClient = openWeatherClient;
        this.jsonReaderUtil = jsonReaderUtil;
        this.comfortIndexService = comfortIndexService;
    }

    //Method
    public List<CityWeatherResult> getWeatherResult(){
        logger.info("Starting weather analytics process");

        List<City> cities = jsonReaderUtil.readCitiesFromjson();
        List<CityWeatherResult> results = new ArrayList<>();

        for(City city : cities){
            logger.info("Processing city : {}",city.getCityName());

            JsonNode weatherData = openWeatherClient.getWeatherByCityID(city.getCityCode());

            if(weatherData == null){
                logger.warn("No data is found from city : ",city.getCityName());
                throw new WeatherExceptions("Weather data not found for city: " + city.getCityName());
            }

            double temperature = weatherData.get("main")
                                        .get("temp")
                                        .asDouble();

            String status = weatherData.get("weather")
                                .get(0)
                                .get("main")
                                .asText();

            city.setTemp(temperature);
            city.setStatus(status);

            String comfortIndex = comfortIndexService.calculateComfortindex(city);

            CityWeatherResult result = new CityWeatherResult(city.getCityName(),
                                                    temperature,comfortIndex);

            results.add(result);
        }
        logger.info("Weather analytics completed successfully");
        return results;
    }
}
