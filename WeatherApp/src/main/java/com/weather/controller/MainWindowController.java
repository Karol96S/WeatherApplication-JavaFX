package com.weather.controller;

import com.weather.WeatherMenager;
import com.weather.views.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    public MainWindowController(WeatherMenager weatherMenager, MainWindow mainWindow, String fxmlName) {
        super(weatherMenager, mainWindow, fxmlName);
    }

    @FXML
    private ImageView DayOneDestinationImage;

    @FXML
    private Label TempDestinationDayOne;

    @FXML
    private Label TempStartDayOne;

    @FXML
    private ImageView dayOneStartImage;

    @FXML
    private TextField destinationCity;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField startCity;

    @FXML
    void checkWeatherButtonAction() {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
