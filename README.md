# 🌦 Weather Analytics Service

A Spring Boot-based backend application that collects weather data from the OpenWeatherMap API for multiple cities, processes the data, and generates comfort index analytics for each city.

The project demonstrates clean architecture, API integration, caching, logging with AOP, exception handling, and modular service design.

## 🎯 Project Purpose

The purpose of this project is to build a weather analytics microservice that:

- Retrieves weather information for multiple cities
- Processes and analyzes temperature and weather conditions
- Calculates a Comfort Index for each city
- Improves performance using caching
- Implements AOP logging for monitoring application flow
- Uses global exception handling for clean error responses

This project demonstrates enterprise-level backend development practices using Spring Boot.

## ⚙️ Core Features
- 1️⃣ Weather Data Fetching
    - Fetches real-time weather data from OpenWeatherMap API
    - Uses city IDs stored in a local JSON file

- 2️⃣ Weather Analytics
    - Processes weather information including:
    - Temperature
    - Weather status
    - Comfort level for the city

- 3️⃣ Comfort Index Calculation
    - Determines how comfortable the weather is for humans based on temperature and weather conditions.

- 4️⃣ API Endpoint
    - Provides REST API endpoints to retrieve analyzed weather data.

- 5️⃣ Performance Optimization
    - Uses Caffeine Cache to store weather results temporarily and reduce external API calls.

- 6️⃣ Logging & Monitoring
    - Implements Spring AOP LoggingAspect to automatically log:
    - Method entry
    - Method exit
    - Exceptions

- 7️⃣ Global Exception Handling
    - Centralized error handling using @ControllerAdvice.

## 🧰 Tech Stack
- Java 17+
- Spring Boot
- Spring Web
- Caffeine Cache
- OpenWeatherMap API
- Maven

## 📂 Project Structure

### 📦 Backend Packages

---

⚙️ **configs → Cache configuration**  
*(CacheConfig.java)*  
  - Configures Caffeine caching for weather API responses  
  - Reduces repeated API calls to improve performance  
  - Cache settings:  
    - Cache name: `weatherCache`  
    - Expiration: 5 minutes  
    - Maximum entries: 1000  

---

🌐 **controllers → REST API endpoints**  
*(WeatherController.java)*  
  - Exposes REST endpoints for the application  
  - Handles incoming HTTP requests  
  - Calls the service layer to process business logic  
  - **Example Endpoint:**  
    - `GET /api/weather/analytics`  
      Returns weather analytics results for all cities  

---

🧠 **services → Business logic**  

*(WeatherService.java)*  
  - Main business logic layer  
  - Responsibilities:  
    - Read cities from `cities.json`  
    - Call the weather API  
    - Extract temperature and weather condition  
    - Calculate Comfort Index  
    - Build response objects  

*(ComfortIndexService.java)*  
  - Calculates Comfort Index based on:  
    - Temperature  
    - Weather condition  

---

🔗 **client → External API integration**  
*(OpenWeatherClient.java)*  
  - Handles communication with the OpenWeather API  
  - Responsibilities:  
    - Construct API request URL  
    - Send HTTP requests  
    - Retrieve JSON weather response  

---

🗂 **models → Entity classes**  

*(City.java)*  
  - Represents city information  
  - Fields:  
    - `cityCode`  
    - `cityName`  
    - `temp`  
    - `status`  

---

📦 **dto → Data Transfer Objects**  
*(CityWeatherResult.java)*  
  - Represents processed weather analytics returned by the API  
  - Fields:  
    - City Name  
    - Temperature  
    - Weather Status  
    - Comfort Index  

---

🛠 **utils → Utility helpers**  
*(JsonReaderUtil.java)*  
  - Reads city information from the JSON file  
  - Responsibilities:  
    - Load `cities.json` from resources  
    - Convert JSON data to Java objects  
    - Provide list of cities to services  

---

📊 **aspects → AOP logging**  
*(LoggingAspect.java)*  
  - Implements Aspect-Oriented Programming (AOP) logging  
  - Automatically logs:  
    - Method start  
    - Method completion  
    - Exceptions  
  - **Example Logs:**  
    - `Entering WeatherService.getWeatherAnalytics`  
    - `Exiting WeatherService.getWeatherAnalytics`  

---

🚨 **exceptions → Custom exception handling**  

*(WeatherException.java)*  
  - Custom exception for weather-related errors  
  - Example cases:  
    - Weather API failure  
    - Missing weather data  
    - Invalid city information  

*(GlobalExceptionHandler.java)*  
  - Centralized exception handler for the application  
  - Returns standardized error responses for API clients  
