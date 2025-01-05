package com.CRUD_Agriculture.CRUD_Agriculture.controllers;

import com.CRUD_Agriculture.CRUD_Agriculture.model.response.ForecastResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.WeatherResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.services.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200") // Đảm bảo URL frontend đúng
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String city) {
        WeatherResponse response = weatherService.getWeatherData(city);
        return ResponseEntity.ok(response);
    }

    // Endpoint lấy dự báo 5 ngày
    @GetMapping("/forecast/city")
    public ResponseEntity<ForecastResponse> get5DayForecastByCity(@RequestParam String city) {
        ForecastResponse response = weatherService.get5DayForecastByCity(city);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/weather-and-forecast")
    public ResponseEntity<Map<String, Object>> getWeatherAndForecast(@RequestParam String city) {
        Map<String, Object> result = new HashMap<>();
        result.put("currentWeather", weatherService.getWeatherData(city));
        result.put("forecast", weatherService.get5DayForecastByCity(city));
        return ResponseEntity.ok(result);
    }


}
