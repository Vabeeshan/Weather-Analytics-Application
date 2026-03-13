package com.weather.Weather_Analytics.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Auto create all getters and setters
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Para Constructor
public class City {
    private String CityCode;
    private String CityName;
    private Double Temp;
    private Double humidity;
    private Double windSpeed;
    private String Status;

}
