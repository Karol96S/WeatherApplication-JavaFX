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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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

//    private void updateStartingCityForecastBETA() throws IOException, InterruptedException {
//        String uriStart = Connector.buildApiRequest(startCity.getText());
//        String rawDataStart = Connector.sendRequest(uriStart);
//        WeatherData weatherDataStartCity = new WeatherData(rawDataStart);
//
//        List<Double> startCityDayTemperatures = weatherDataStartCity.getCityMaxTemperatures();
//        List<DayOfWeek> daysOfTheWeek = weatherDataStartCity.getDays();
//        tempStartDayOne.setText(startCityDayTemperatures.get(0).toString());
//        dayOne.setText(Date.translatedDayOfTheWeek(daysOfTheWeek.get(0)));
//        Image image = new Image(getClass().getResourceAsStream("/icons/clear-day.png"));
//        dayOneStartImage.setImage(image);
//    }

    private void updateStartingCityForecast() throws IOException, InterruptedException {
        String uriStart = Connector.buildApiRequest(startCity.getText());
        String rawDataStart = Connector.sendRequest(uriStart);
        WeatherData weatherDataStartCity = new WeatherData(rawDataStart);

        List<Double> startCityDayTemperatures = weatherDataStartCity.getCityMaxTemperatures();
        List<DayOfWeek> daysOfTheWeek = weatherDataStartCity.getDays();
        List<String> icons = weatherDataStartCity.getIconTypes();

        populateDaysOfTheWeek(daysOfTheWeek);
        populateIcons(icons);
    }

    private void populateDaysOfTheWeek(List<DayOfWeek> dayOfWeeks) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            Label label = new Label();
            String day = Date.translatedDayOfTheWeek(dayOfWeeks.get(row));
            label.setText(day);
            weatherGrid.add(label, 0, row);
        }
    }

    private void populateIcons(List<String> icons) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            ImageView imageView = new ImageView();
            String icon = icons.get(row);
            System.out.println(icon);
            String imagePath = getIconPath(icon);
            System.out.println(imagePath);
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
            weatherGrid.add(imageView, 1, row);
        }
    }

    private String getIconPath(String icon) {

        String imagePath = "/icons/";
        switch (icon) {
            case "clear-day":
                imagePath += "clear-day.png";
                break;
            case "clear-night":
                imagePath += "clear-night.png";
                break;
            case "cloudy":
                imagePath += "cloudy.png";
                break;
            case "fog":
                imagePath += "fog.png";
                break;
            case "hail":
                imagePath += "hail.png";
                break;
            case "partly-cloudy-day":
                imagePath += "partly-cloudy-day.png";
                break;
            case "partly-cloudy-night":
                imagePath += "partly-cloudy-night.png";
                break;
            case "rain":
                imagePath += "rain.png";
                break;
            case "rain-snow":
                imagePath += "rain-snow.png";
                break;
            case "rain-snow-shower-night":
                imagePath += "rain-snow-shower-night.png";
                break;
            case "rain-snow-shower-day":
                imagePath += "rain-snow-shower-day.png";
                break;
            case "showers-day":
                imagePath += "showers-day.png";
                break;
            case "showers-night":
                imagePath += "showers-night.png";
                break;
            case "sleet":
                imagePath += "sleet.png";
                break;
            case "snow":
                imagePath += "snow.png";
                break;
            case "snow-showers-day":
                imagePath += "snow-showers-day.png";
                break;
            case "snow-showers-night":
                imagePath += "snow-showers-night.png";
                break;
            case "thunger":
                imagePath += "thunder.png";
                break;
            case "thunder-rain":
                imagePath += "thunder-rain.png";
                break;
            case "thunder-showers-day":
                imagePath += "thunder-showers-day.png";
                break;
            case "thunder-showers-night":
                imagePath += "thunder-showers-night.png";
                break;
            case "wind":
                imagePath += "wind.png";
                break;
            default:
                imagePath = imagePath;
                break;
        }
        return imagePath;
    }

//    private void populateTemperatures(List<Double> startCityTemperatures) {
//        for(int row = 0; row <= weatherGrid.getRowCount(); row++) {
//            Text text = new Text();
//            text.setText(startCityTemperatures.get(0).toString());
//
//        }
//    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
