/*
    Purpose:
        Encapsulate all API calls to OpenWeatherMap in a single client class.
        Make code modular, reusable, and testable.
        Allow server-side caching to be added easily in this layer.
        Return parsed JSON / Java objects instead of raw HTTP responses.

    Why you need it:
        Keeps service classes clean (WeatherService doesn’t need HTTP code).
        Handles authentication with API key in one place.
        Makes it easier to mock API calls for testing.
 */

package com.weather.Weather_Analytics.Client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenWeatherClient {

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherClient.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    //Inject API key from Application properties
    @Value("${openweathermap.api.key}")
    private String apiKey;

    //URL
    private static final String weather_URL = "https://api.openweathermap.org/data/2.5/weather";

    public OpenWeatherClient(){
        this.restTemplate   = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public JsonNode getWeatherByCityID(String cityID){
       try{
           String url = UriComponentsBuilder.fromHttpUrl(weather_URL)
                   .queryParam("id",cityID)
                   .queryParam("apid",apiKey)
                   .queryParam("units","metric")
                   .toUriString();

           // make API call
           String response = restTemplate.getForObject(url,String.class);

           // Convert Json String to Json Node
           JsonNode jsonNode = objectMapper.readTree(response);

           logger.info("Weather data fetched successfully for cityId={}", cityID);
           return jsonNode;
       }
       catch (Exception e){
           logger.error("Error fetching weather for cityId={}: {}", cityID, e.getMessage());
           return null; // or throw a custom exception if needed
       }
    }

}
