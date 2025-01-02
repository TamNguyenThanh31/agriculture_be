package com.CRUD_Agriculture.CRUD_Agriculture.services;

import com.CRUD_Agriculture.CRUD_Agriculture.model.response.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherData(String city);
}
