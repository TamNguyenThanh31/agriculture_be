package com.CRUD_Agriculture.CRUD_Agriculture.services.impl;

import com.CRUD_Agriculture.CRUD_Agriculture.configurations.WeatherConfig;
import com.CRUD_Agriculture.CRUD_Agriculture.exception.ResourceNotFoundException;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.ForecastResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.GeoResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.WeatherResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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

            // Log URL để kiểm tra
            System.out.println("Calling OpenWeatherMap API with URL: " + url);

            return restTemplate.getForObject(url, WeatherResponse.class);

        } catch (RestClientException e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
            throw new ResourceNotFoundException("Could not fetch weather data for city: " + city);
        }
    }

    @Override
    public ForecastResponse get5DayForecastByCity(String city) {
        // Gọi Geo API để tìm tọa độ
        String geoUrl = String.format("%s/geo/1.0/direct?q=%s&limit=1&appid=%s",
                weatherConfig.getUrl(), city, weatherConfig.getKey());

        GeoResponse[] geoResponse = restTemplate.getForObject(geoUrl, GeoResponse[].class);

        if (geoResponse != null && geoResponse.length > 0) {
            double lat = geoResponse[0].getLat();
            double lon = geoResponse[0].getLon();

            // Gọi phương thức lấy dự báo thời tiết với tên thành phố
            return get5DayForecast(city);
        } else {
            throw new ResourceNotFoundException("City not found: " + city);
        }
    }


    @Override
    public ForecastResponse get5DayForecast(String city) {
        try {
            String url = String.format("%s/data/2.5/forecast?q=%s&appid=%s&units=metric",
                    weatherConfig.getUrl(), city, weatherConfig.getKey());

            // Log URL để kiểm tra
            System.out.println("Calling OpenWeatherMap API with URL: " + url);

            return restTemplate.getForObject(url, ForecastResponse.class);

        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching forecast data: " + e.getResponseBodyAsString());
            throw new ResourceNotFoundException("Could not fetch forecast data for city: " + city);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Could not fetch forecast data for city: " + city);
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
