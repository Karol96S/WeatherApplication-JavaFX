module WeatherApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.desktop;
    requires org.apache.httpcomponents.httpclient;
    requires java.net.http;

    opens com.weather;
    opens com.weather.controller;
    opens com.weather.model;
}