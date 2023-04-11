package com.weather.controller;

import com.weather.model.WeatherData;
import com.weather.model.WeatherService;
import com.weather.model.WeatherServiceFactory;
import com.weather.views.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    public MainWindowController(MainWindow mainWindow, String fxmlName) {
        super(mainWindow, fxmlName);
    }

    private WeatherService weatherService;

    @FXML
    private Label mainTitle;

    @FXML
    private TextField destinationCity;

    @FXML
    private Label destinationCityDisplayLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField startCity;

    @FXML
    private Label startCityDisplayLabel;

    @FXML
    private GridPane weatherGrid;

    @FXML
    private GridPane citiesGrid;

    @FXML
    void checkWeatherButtonAction() {
        errorLabel.setText("");

        if ((startCity.getText().length() > 0) && (destinationCity.getText().length() > 0)) {
        WeatherData weatherDataStart = weatherService.getWeather(startCity.getText());
        WeatherData weatherDataEnd = weatherService.getWeather(destinationCity.getText());
        displayWeather(weatherDataStart, weatherDataEnd);
        } else {
            errorLabel.setText("Pola nie mogą być puste!");
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }

    private void displayWeather(WeatherData weatherDataStart, WeatherData weatherDataEnd) {
        if ((weatherDataStart.statusCode == 200) && (weatherDataEnd.statusCode == 200)) {
            startCityDisplayLabel.setText(startCity.getText());
            destinationCityDisplayLabel.setText(destinationCity.getText());

            mainWindow.populateDaysOfTheWeek(weatherDataStart.getDates(), weatherDataStart.getDays(), weatherGrid);
            mainWindow.populateStartCityIcons(weatherDataStart.getIconTypes(), weatherGrid);
            mainWindow.populateStartCityTemperatures(weatherDataStart.cityMaxTemperatures, weatherDataStart.cityMinTemperatures, weatherGrid);
            mainWindow.populateDestinationCityIcons(weatherDataEnd.getIconTypes(), weatherGrid);
            mainWindow.populateDestinationCityTemperatures(weatherDataEnd.cityMaxTemperatures, weatherDataEnd.cityMinTemperatures, weatherGrid);
        } else {
            int statusCode;
            statusCode = (weatherDataStart.statusCode != 200) ? weatherDataStart.statusCode : weatherDataEnd.statusCode;
            switch(statusCode) {
                case 400:
                    errorLabel.setText("Wpisano złe miasto!");
                    break;
                case 401:
                    errorLabel.setText("Problem z kluczem!");
                    break;
                case 403:
                    errorLabel.setText("Brak dostępu!");
                    break;
                case 404:
                    errorLabel.setText("Problem z serwerem!");
                    break;
                default:
                    errorLabel.setText("Nieznany problem!");
                    break;
            }
        }
    }
}
