package com.weather.Weather_Analytics.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.Weather_Analytics.Models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReaderUtil {

    // Logger is used to track what happens in the system.
    //logger object
    private static final Logger logger = LoggerFactory.getLogger(JsonReaderUtil.class);

    //method
    public List<City> readCitiesFromjson(){

        List<City> cities = new ArrayList<>();

        try{
            logger.info("Starting to read Cities.json file");

            // ObjectMapper (Jackson library) converts JSON → Java Objects.
            ObjectMapper objectMapper = new ObjectMapper();

            // read cities.json file from resources
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("cities.json");

            if(inputStream == null){
                logger.error("cities.json file not found in resources folder");
            }

            // makes the stream into a tree structure
            JsonNode rootNode = objectMapper.readTree(inputStream);

            //Takes only the JSON array with the key "List"
            JsonNode cityList = rootNode.get("List");

            for(JsonNode node : cityList){

                City city = new City();

                city.setCityCode(node.get("CityCode").asText());
                city.setCityName(node.get("CityName").asText());
                city.setTemp(node.get("Temp").asDouble());
                city.setStatus(node.get("Status").asText());

                cities.add(city);

                logger.debug("Cities Loaded : {}",city.getCityName());
            }

            logger.info("Successfully loaded {} cities", cities.size());

        }
        catch (Exception err){
            logger.error("Error occured while reading cities.json file", err);
            throw new RuntimeException("Failed to read JSON file");
        }

        return cities;
    }
}
