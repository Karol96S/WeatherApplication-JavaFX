module WeatherApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.desktop;

    opens com.weather;
    opens com.weather.controller;
    //opens com.weather.model;
}