package com.weather.Weather_Analytics.Exception;

public class WeatherExceptions extends RuntimeException{
    public WeatherExceptions(String message) {
        super(message);
    }

    public WeatherExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
