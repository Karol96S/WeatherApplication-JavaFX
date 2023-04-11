package com.weather.model;

import com.weather.model.client.VisualCrossingClient;
import com.weather.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }

    public static WeatherClient createWeatherClient() {
        return new VisualCrossingClient();
    }
}
