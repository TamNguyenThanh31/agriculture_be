package com.CRUD_Agriculture.CRUD_Agriculture.model.response;

import java.util.List;

public class ForecastResponse {
    private List<Forecast> list;

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }

    public static class Forecast {
        private long dt; // Unix timestamp
        private Main main;
        private List<Weather> weather;

        // Getters và Setters
        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }
    }

    public static class Main {
        private double temp;
        private double humidity;

        // Getters và Setters
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }
    }

    public static class Weather {
        private String main;
        private String description;
        private String icon; // Thêm trường này để ánh xạ mã biểu tượng

        // Getters và Setters
        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
//package com.CRUD_Agriculture.CRUD_Agriculture.model.response;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import java.util.List;
//import java.util.Map;
//
//public class ForecastResponse {
//    private List<Forecast> list;
//
//    public List<Forecast> getList() {
//        return list;
//    }
//
//    public void setList(List<Forecast> list) {
//        this.list = list;
//    }
//
//    /**
//     * Tính nhiệt độ cao nhất trong một ngày
//     */
//    public double getDailyMaxTemperature() {
//        if (list == null || list.isEmpty()) {
//            return 0.0; // Mặc định nếu không có dữ liệu
//        }
//
//        return list.stream()
//                .mapToDouble(forecast -> forecast.getMain().getTemp())
//                .max()
//                .orElse(0.0); // Giá trị mặc định nếu không có giá trị nào
//    }
//
//    /**
//     * Tính nhiệt độ thấp nhất trong một ngày
//     */
//    public double getDailyMinTemperature() {
//        if (list == null || list.isEmpty()) {
//            return 0.0; // Mặc định nếu không có dữ liệu
//        }
//
//        return list.stream()
//                .mapToDouble(forecast -> forecast.getMain().getTemp())
//                .min()
//                .orElse(0.0); // Giá trị mặc định nếu không có giá trị nào
//    }
//
//    /**
//     * Tính lượng mưa trung bình trong một ngày
//     */
//    public double getDailyAverageRain() {
//        if (list == null || list.isEmpty()) {
//            return 0.0; // Không có dữ liệu
//        }
//
//        double totalRain = 0.0;
//        int count = 0;
//
//        for (Forecast forecast : list) {
//            Double rainAmount = forecast.getRainAmount();
//            if (rainAmount != null && rainAmount > 0) {
//                totalRain += rainAmount;
//                count++;
//            }
//        }
//
//        // Tính trung bình
//        return count > 0 ? totalRain / count : 0.0;
//    }
//
//    public static class Forecast {
//        private long dt; // Unix timestamp
//        private Main main;
//        private List<Weather> weather;
//
//        @JsonProperty("rain")
//        private Map<String, Double> rain; // Map chứa các giá trị "1h", "3h"
//
//        // Getters và Setters
//        public long getDt() {
//            return dt;
//        }
//
//        public void setDt(long dt) {
//            this.dt = dt;
//        }
//
//        public Main getMain() {
//            return main;
//        }
//
//        public void setMain(Main main) {
//            this.main = main;
//        }
//
//        public List<Weather> getWeather() {
//            return weather;
//        }
//
//        public void setWeather(List<Weather> weather) {
//            this.weather = weather;
//        }
//
//        public Map<String, Double> getRain() {
//            return rain;
//        }
//
//        public void setRain(Map<String, Double> rain) {
//            this.rain = rain;
//        }
//
//        public Double getRainAmount() {
//            if (rain != null) {
//                // Lấy giá trị từ "3h" hoặc "1h", tùy dữ liệu API
//                return rain.getOrDefault("3h", rain.getOrDefault("1h", 0.0));
//            }
//            return 0.0; // Mặc định nếu không có dữ liệu
//        }
//    }
//
//    public static class Main {
//        private double temp;
//
//        @JsonProperty("temp_min")
//        private double tempMin;
//
//        @JsonProperty("temp_max")
//        private double tempMax;
//
//        private double humidity;
//
//        // Getters và Setters
//        public double getTemp() {
//            return temp;
//        }
//
//        public void setTemp(double temp) {
//            this.temp = temp;
//        }
//
//        public double getTempMin() {
//            return tempMin;
//        }
//
//        public void setTempMin(double tempMin) {
//            this.tempMin = tempMin;
//        }
//
//        public double getTempMax() {
//            return tempMax;
//        }
//
//        public void setTempMax(double tempMax) {
//            this.tempMax = tempMax;
//        }
//
//        public double getHumidity() {
//            return humidity;
//        }
//
//        public void setHumidity(double humidity) {
//            this.humidity = humidity;
//        }
//    }
//
//    public static class Weather {
//        private String main;
//        private String description;
//        private String icon;
//
//        // Getters và Setters
//        public String getMain() {
//            return main;
//        }
//
//        public void setMain(String main) {
//            this.main = main;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getIcon() {
//            return icon;
//        }
//
//        public void setIcon(String icon) {
//            this.icon = icon;
//        }
//    }
//}
