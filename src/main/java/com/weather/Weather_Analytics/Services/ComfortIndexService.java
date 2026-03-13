package com.weather.Weather_Analytics.Services;

import com.weather.Weather_Analytics.Models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ComfortIndexService {

    private static final Logger logger = LoggerFactory.getLogger(ComfortIndexService.class);

    public String calculateComfortindex(City city){
        logger.info("Calculating comfort index for city: {}", city.getCityName());

        double temperature = city.getTemp();
        String status = city.getStatus();

        if (temperature >= 22 && temperature <= 27 && status.equalsIgnoreCase("Clear")) {
            return "High Comfort";
        }
        else if (temperature >= 28 && temperature <= 32 && status.equalsIgnoreCase("Clouds")) {
            return "Medium Comfort";
        }
        else if (temperature > 32) {
            return "Low Comfort";
        }
        else {
            return "Moderate Comfort";
        }
    }
}
