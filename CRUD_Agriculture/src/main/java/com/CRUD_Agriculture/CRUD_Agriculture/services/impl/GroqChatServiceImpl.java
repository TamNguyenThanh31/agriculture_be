package com.CRUD_Agriculture.CRUD_Agriculture.services.impl;

import com.CRUD_Agriculture.CRUD_Agriculture.model.response.ForecastResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.WeatherResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.services.GroqChatService;
import com.CRUD_Agriculture.CRUD_Agriculture.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GroqChatServiceImpl implements GroqChatService {

    private final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private final String API_KEY = "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"; // Thay bằng API Key thực tế

    private final WeatherService weatherService;

    @Autowired
    public GroqChatServiceImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public String getGroqResponse(String userMessage) {
        // Kiểm tra câu hỏi về thời tiết hôm nay
        if (userMessage.toLowerCase().contains("thời tiết hôm nay")) {
            String city = extractCityFromMessage(userMessage);
            try {
                WeatherResponse weather = weatherService.getWeatherData(city);
                return formatCurrentWeatherResponse(weather, city);
            } catch (Exception e) {
                return "Không tìm thấy thông tin thời tiết cho thành phố: " + city;
            }
        }

        // Kiểm tra câu hỏi về dự báo thời tiết
        if (userMessage.toLowerCase().contains("dự báo thời tiết")) {
            String city = extractCityFromMessage(userMessage);
            try {
                ForecastResponse forecast = weatherService.get5DayForecast(city);
                return formatForecastResponse(forecast, city);
            } catch (Exception e) {
                return "Không tìm thấy dự báo thời tiết cho thành phố: " + city;
            }
        }

        // Nếu không liên quan đến thời tiết, gửi đến Groq API
        return askGroqAPI(userMessage);
    }

    // Gửi câu hỏi đến Groq API
    private String askGroqAPI(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();

        // Tạo Request Body
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", userMessage);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", List.of(message));
        requestBody.put("model", "llama-3.3-70b-versatile");

        // Tạo Header
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(
                    API_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            // Xử lý phản hồi từ Groq
            if (responseEntity.getBody() != null && responseEntity.getBody().containsKey("choices")) {
                Map<String, Object> firstChoice = ((List<Map<String, Object>>) responseEntity.getBody().get("choices")).get(0);
                Map<String, Object> messageResponse = (Map<String, Object>) firstChoice.get("message");
                return (String) messageResponse.get("content");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Có lỗi xảy ra khi gửi yêu cầu đến Groq.";
        }

        return "Không nhận được phản hồi từ Groq API.";
    }

    // Trích xuất tên thành phố từ câu hỏi
    private String extractCityFromMessage(String userMessage) {
        // Bạn có thể cải tiến logic để trích xuất thành phố từ câu hỏi
        return "Hanoi"; // Mặc định là Hà Nội nếu không có tên thành phố trong câu hỏi
    }

    // Định dạng phản hồi thời tiết hiện tại
    private String formatCurrentWeatherResponse(WeatherResponse weather, String city) {
        String weatherDescription = "Không rõ";
        if (weather.getWeather() != null && weather.getWeather().length > 0) {
            weatherDescription = weather.getWeather()[0].getDescription();
        }

        double temp = weather.getMain() != null ? weather.getMain().getTemp() : 0.0;
        double humidity = weather.getMain() != null ? weather.getMain().getHumidity() : 0.0;
        double pressure = weather.getMain() != null ? weather.getMain().getPressure() : 0.0;
        double windSpeed = weather.getWind() != null ? weather.getWind().getSpeed() : 0.0;

        return String.format(
                "Thời tiết tại %s: %s, nhiệt độ: %.1f°C, độ ẩm: %.1f%%, áp suất: %.1f hPa, gió: %.1f m/s.",
                city,
                weatherDescription,
                temp,
                humidity,
                pressure,
                windSpeed
        );
    }


    // Định dạng phản hồi dự báo thời tiết 5 ngày
    private String formatForecastResponse(ForecastResponse forecast, String city) {
        StringBuilder response = new StringBuilder();
        response.append(String.format("Dự báo thời tiết 5 ngày tới tại %s:\n", city));
        for (ForecastResponse.Forecast day : forecast.getList()) {
            response.append(String.format(
                    "- Ngày %s: %s, nhiệt độ: %.1f°C, độ ẩm: %.1f%%.\n",
                    new SimpleDateFormat("dd/MM/yyyy").format(new Date(day.getDt() * 1000L)),
                    day.getWeather().get(0).getDescription(),
                    day.getMain().getTemp(),
                    day.getMain().getHumidity()
            ));
        }
        return response.toString();
    }
}
