package com.weather.controller;

import com.weather.model.Connector;
import com.weather.model.WeatherData;
import com.weather.views.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    public MainWindowController(MainWindow mainWindow, String fxmlName) {
        super(mainWindow, fxmlName);
    }

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
        updateStartingCityForecast();
        updateDestinationCityForecast();
    }

    private void updateStartingCityForecast() {
        if (startCity.getText().length() > 0) {
            String uriStart = Connector.buildApiRequest(startCity.getText());
            String rawDataStart = Connector.sendRequest(uriStart);

            if (rawDataStart != null) {
                WeatherData weatherDataStartCity = new WeatherData(rawDataStart);

                List<Double> startCityDayTemperatures = weatherDataStartCity.getCityMaxTemperatures();
                List<Double> startCityNightTemperatures = weatherDataStartCity.getCityMinTemperatures();
                List<DayOfWeek> daysOfTheWeek = weatherDataStartCity.getDays();
                List<ZonedDateTime> calendarDays = weatherDataStartCity.getDates();
                List<String> icons = weatherDataStartCity.getIconTypes();

                startCityDisplayLabel.setText(startCity.getText());
                mainWindow.populateDaysOfTheWeek(calendarDays, daysOfTheWeek, weatherGrid);
                mainWindow.populateStartCityIcons(icons, weatherGrid);
                mainWindow.populateStartCityTemperatures(startCityDayTemperatures, startCityNightTemperatures, weatherGrid);
            } else {
                int statusCode = Connector.getInstance().getErrorCode();
                switch (statusCode) {
                    case 400:
                        errorLabel.setText("Takie miasto startowe nie istnieje!");
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
        } else {
            errorLabel.setText("Pola nie mogą być puste!");
        }
    }

    private void updateDestinationCityForecast() {
        if (destinationCity.getText().length() > 0) {
            String uriDestination = Connector.buildApiRequest(destinationCity.getText());
            String rawDataDestination = Connector.sendRequest(uriDestination);

            if (rawDataDestination != null) {
            WeatherData weatherDataDestinationCity = new WeatherData(rawDataDestination);

            List<Double> destinationCityDayTemperatures = weatherDataDestinationCity.getCityMaxTemperatures();
            List<Double> destinationCityNightTemperatures = weatherDataDestinationCity.getCityMinTemperatures();
            List<String> icons = weatherDataDestinationCity.getIconTypes();

            destinationCityDisplayLabel.setText(destinationCity.getText());
            mainWindow.populateDestinationCityIcons(icons, weatherGrid);
            mainWindow.populateDestinationCityTemperatures(destinationCityDayTemperatures, destinationCityNightTemperatures, weatherGrid);
            } else {
                int statusCode = Connector.getInstance().getErrorCode();
                switch(statusCode) {
                    case 400:
                        errorLabel.setText("Takie miasto docelowe nie istnieje!");
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
        } else {
            errorLabel.setText("Pola nie mogą być puste!");
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
