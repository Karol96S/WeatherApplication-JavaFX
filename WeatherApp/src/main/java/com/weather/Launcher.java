package com.weather;

import com.weather.views.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class Launcher extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    private WeatherMenager weatherMenager = new WeatherMenager();

    public void start(Stage primaryStage) throws Exception {
        MainWindow mainWindow = new MainWindow(weatherMenager);
        mainWindow.showMainWindow();
    }
}
