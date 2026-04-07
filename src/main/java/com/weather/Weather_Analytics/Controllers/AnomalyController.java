package com.weather.Weather_Analytics.Controllers;

import com.weather.Weather_Analytics.DTO.AnomalyRequestCities;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/weather")
public class AnomalyController {

    @PostMapping("/anomaly-detect")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getAnomalyDetection(@RequestBody AnomalyRequestCities anomalyRequestCities) {
        try {
            // 🔹 Python Flask API URL
            String pythonUrl = "http://localhost:5000/api/anomaly/detect";

            RestTemplate restTemplate = new RestTemplate();

            // 🔹 Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 🔹 Wrap DTO into HTTP request
            HttpEntity<AnomalyRequestCities> requestEntity =
                    new HttpEntity<>(anomalyRequestCities, headers);

            // 🔹 Call Python API
            ResponseEntity<String> response = restTemplate.exchange(
                    pythonUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // 🔹 Return Python response to frontend
            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error calling Python service: " + e.getMessage());
        }


    }
}
