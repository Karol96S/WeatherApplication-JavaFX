package com.weather.model;

import com.weather.model.client.WeatherClient;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherData getWeather(String cityName) {
        return weatherClient.getWeather(cityName);
    }
}
