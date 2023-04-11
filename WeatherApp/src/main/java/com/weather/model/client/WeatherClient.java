package com.weather.model.client;

import com.weather.model.WeatherData;

public interface WeatherClient {
    WeatherData getWeather(String cityName);
}
