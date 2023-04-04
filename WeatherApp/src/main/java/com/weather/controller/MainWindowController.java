package com.weather.controller;

import com.weather.WeatherMenager;
import com.weather.model.Connector;
import com.weather.model.Date;
import com.weather.model.WeatherData;
import com.weather.views.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    public MainWindowController(WeatherMenager weatherMenager, MainWindow mainWindow, String fxmlName) {
        super(weatherMenager, mainWindow, fxmlName);
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
    void checkWeatherButtonAction() throws IOException, InterruptedException {
        startCityDisplayLabel.setText(startCity.getText());
        updateStartingCityForecast();

        destinationCityDisplayLabel.setText(destinationCity.getText());
        updateDestinationCityForecast();
    }

    private void updateStartingCityForecast() throws IOException, InterruptedException {
        String uriStart = Connector.buildApiRequest(startCity.getText());
        String rawDataStart = Connector.sendRequest(uriStart);
        WeatherData weatherDataStartCity = new WeatherData(rawDataStart);

        List<Double> startCityDayTemperatures = weatherDataStartCity.getCityMaxTemperatures();
        List<Double> startCityNightTemperatures = weatherDataStartCity.getCityMinTemperatures();
        List<DayOfWeek> daysOfTheWeek = weatherDataStartCity.getDays();
        List<ZonedDateTime> calendarDays = weatherDataStartCity.getDates();
        List<String> icons = weatherDataStartCity.getIconTypes();

        mainWindow.populateDaysOfTheWeek(calendarDays, daysOfTheWeek, weatherGrid);
        mainWindow.populateStartCityIcons(icons, weatherGrid);
        mainWindow.populateStartCityTemperatures(startCityDayTemperatures, startCityNightTemperatures, weatherGrid);

    }

    private void updateDestinationCityForecast() throws IOException, InterruptedException {

        String uriDestination = Connector.buildApiRequest(destinationCity.getText());
        String rawDataDestination = Connector.sendRequest(uriDestination);
        WeatherData weatherDataDestinationCity = new WeatherData(rawDataDestination);

        List<Double> destinationCityDayTemperatures = weatherDataDestinationCity.getCityMaxTemperatures();
        List<Double> destinationCityNightTemperatures = weatherDataDestinationCity.getCityMinTemperatures();
        List<DayOfWeek> daysOfTheWeek = weatherDataDestinationCity.getDays();
        List<String> icons = weatherDataDestinationCity.getIconTypes();

        mainWindow.populateDestinationCityIcons(icons, weatherGrid);
        mainWindow.populateDestinationCityTemperatures(destinationCityDayTemperatures, destinationCityNightTemperatures, weatherGrid);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
