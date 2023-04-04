package com.weather.controller;

import com.weather.views.MainWindow;

public class BaseController {

    protected MainWindow mainWindow;
    private String fxmlName;

    public BaseController(MainWindow mainWindow, String fxmlName) {
        this.mainWindow = mainWindow;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }

}
