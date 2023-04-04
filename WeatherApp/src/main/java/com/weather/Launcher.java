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

    public void start(Stage primaryStage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.showMainWindow();
    }
}
