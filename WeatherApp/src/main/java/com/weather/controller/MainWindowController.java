package com.weather.controller;

import com.weather.WeatherMenager;
import com.weather.model.Connector;
import com.weather.model.Date;
import com.weather.model.WeatherData;
import com.weather.views.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    public MainWindowController(WeatherMenager weatherMenager, MainWindow mainWindow, String fxmlName) {
        super(weatherMenager, mainWindow, fxmlName);
    }

    @FXML
    private ImageView dayOneDestinationImage;

    @FXML
    private Label tempDestinationDayOne;

    @FXML
    private Label tempStartDayOne;

    @FXML
    private ImageView dayOneStartImage;

    @FXML
    private TextField destinationCity;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField startCity;

    @FXML
    private Label startCityDisplayLabel;

    @FXML
    private Label destinationCityDisplayLabel;

    @FXML
    private Label dayOne;

    @FXML
    private Label dayTwo;

    @FXML
    private Label dayThree;

    @FXML
    private Label dayFour;

    @FXML
    private Label dayFive;

    @FXML
    void checkWeatherButtonAction() throws IOException, InterruptedException {
        startCityDisplayLabel.setText(startCity.getText());
        updateStartingCityForecast();
//        String uriStart = Connector.buildApiRequest(startCity.getText());
//        String rawDataStart = Connector.sendRequest(uriStart);
//        WeatherData weatherDataStartCity = new WeatherData(rawDataStart);


        destinationCityDisplayLabel.setText(destinationCity.getText());
        String uriDestination = Connector.buildApiRequest(startCity.getText());
        String rawDataDestination = Connector.sendRequest(uriDestination);
        WeatherData weatherDataDestinationCity = new WeatherData(rawDataDestination);

//        List<Double> startCityDayTemperatures = weatherDataStartCity.getCityMaxTemperatures();
//        List<DayOfWeek> daysOfTheWeek = weatherDataStartCity.getDays();
//        tempStartDayOne.setText(startCityDayTemperatures.get(0).toString());
//        dayOne.setText(Date.translatedDayOfTheWeek(daysOfTheWeek.get(0)));
//        Image image = new Image(getClass().getResourceAsStream("/icons/clear-day.png"));
//        dayOneStartImage.setImage(image);

    }

    private void updateStartingCityForecast() throws IOException, InterruptedException {
        String uriStart = Connector.buildApiRequest(startCity.getText());
        String rawDataStart = Connector.sendRequest(uriStart);
        WeatherData weatherDataStartCity = new WeatherData(rawDataStart);

        List<Double> startCityDayTemperatures = weatherDataStartCity.getCityMaxTemperatures();
        List<DayOfWeek> daysOfTheWeek = weatherDataStartCity.getDays();
        tempStartDayOne.setText(startCityDayTemperatures.get(0).toString());
        dayOne.setText(Date.translatedDayOfTheWeek(daysOfTheWeek.get(0)));
        Image image = new Image(getClass().getResourceAsStream("/icons/clear-day.png"));
        dayOneStartImage.setImage(image);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
