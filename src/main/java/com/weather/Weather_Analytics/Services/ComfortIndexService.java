package com.weather.Weather_Analytics.Services;

import com.weather.Weather_Analytics.DTO.CityWeatherResult;
import com.weather.Weather_Analytics.Models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ComfortIndexService {

    private static final Logger logger = LoggerFactory.getLogger(ComfortIndexService.class);

    public CityWeatherResult calculateComfortindex(City city){
        logger.info("Calculating comfort index for city: {}", city.getCityName());

        // 1️⃣ Retrieve weather parameters
        double temperature = city.getTemp();        // in Celsius
        double humidity = city.getHumidity();       // in percentage
        double windSpeed = city.getWindSpeed();     // in m/s

        double comfortScore = (27 - Math.abs(temperature - 25)) * 0.5  // temperature contribution
                + (100 - humidity) * 0.3                    // humidity contribution
                - windSpeed * 0.2;                           // wind contribution

        logger.info("Temperature: {}, Humidity: {}, Wind Speed: {}, Comfort Score: {}",
                temperature, humidity, windSpeed, comfortScore);

        CityWeatherResult transferngWeatherResult = new CityWeatherResult();
        transferngWeatherResult.setComfortScore(comfortScore);

        // 3️⃣ Convert numeric score to descriptive level
        if (comfortScore >= 70) {
            transferngWeatherResult.setRank("High Comfort");
        } else if (comfortScore >= 50) {
            transferngWeatherResult.setRank("Moderate Comfort");
        } else if (comfortScore >= 30) {
            transferngWeatherResult.setRank("Low Comfort");
        } else {
            transferngWeatherResult.setRank("Very Low Comfort");
        }

        return transferngWeatherResult;
    }
}
