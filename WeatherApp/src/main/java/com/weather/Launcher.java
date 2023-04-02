package com.weather;

import com.weather.model.Connector;
import com.weather.model.WeatherData;
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
        //check for conditions if city is correct etc.
        MainWindow mainWindow = new MainWindow(weatherMenager);
        //Connector.sendRequest(Connector.buildApiRequest("Warszawa"));
        //WeatherData.prepareApiData(Connector.sendRequest(Connector.buildApiRequest("Warszawa")));
        //Connector.prepareApiData();
        mainWindow.showMainWindow();
    }
}
