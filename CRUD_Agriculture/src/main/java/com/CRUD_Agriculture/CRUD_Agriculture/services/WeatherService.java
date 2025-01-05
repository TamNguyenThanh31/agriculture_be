package com.CRUD_Agriculture.CRUD_Agriculture.services;

import com.CRUD_Agriculture.CRUD_Agriculture.model.response.ForecastResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherData(String city);

    ForecastResponse get5DayForecast(String city); // Thêm phương thức mới

    ForecastResponse get5DayForecastByCity(String city);
}
