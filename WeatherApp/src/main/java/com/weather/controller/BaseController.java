package com.weather.controller;

import com.weather.WeatherMenager;
import com.weather.views.MainWindow;

public class BaseController {

    protected WeatherMenager weatherMenager;
    protected MainWindow mainWindow;
    private String fxmlName;

    public BaseController(WeatherMenager weatherMenager, MainWindow mainWindow, String fxmlName) {
        this.weatherMenager = weatherMenager;
        this.mainWindow = mainWindow;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }

}
