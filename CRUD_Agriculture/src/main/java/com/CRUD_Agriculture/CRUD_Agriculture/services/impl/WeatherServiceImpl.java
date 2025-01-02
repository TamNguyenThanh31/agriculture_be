package com.CRUD_Agriculture.CRUD_Agriculture.services.impl;

import com.CRUD_Agriculture.CRUD_Agriculture.configurations.WeatherConfig;
import com.CRUD_Agriculture.CRUD_Agriculture.exception.ResourceNotFoundException;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.WeatherResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.services.WeatherService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate, WeatherConfig weatherConfig) {
        this.restTemplate = restTemplate;
        this.weatherConfig = weatherConfig;
    }

    @Override
    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherResponse getWeatherData(String city) {
        try {
            String url = String.format("%s/data/2.5/weather?q=%s&appid=%s&units=metric",
                    weatherConfig.getUrl(), city, weatherConfig.getKey());

//            log.info("Calling OpenWeatherMap API with URL: {}", url); // thêm log để debug
            return restTemplate.getForObject(url, WeatherResponse.class);

        } catch (RestClientException e) {
//            log.error("Error fetching weather data for city {}: {}", city, e.getMessage());
            throw new ResourceNotFoundException("Could not fetch weather data for city: " + city);
        }
    }

    // Thêm phương thức để xóa cache khi cần
    @CacheEvict(value = "weatherCache", key = "#city")
    public void clearWeatherCache(String city) {
//        log.info("Clearing weather cache for city: {}", city);
    }

    // Xóa tất cả cache
    @CacheEvict(value = "weatherCache", allEntries = true)
    public void clearAllWeatherCache() {
//        log.info("Clearing all weather cache");
    }
}
