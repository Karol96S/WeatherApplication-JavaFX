package com.weather.views;

import com.weather.WeatherMenager;
import com.weather.controller.BaseController;
import com.weather.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindow {

    private WeatherMenager weatherMenager;
    private ArrayList<Stage> activeStages;

    public MainWindow(WeatherMenager weatherMenager) {
        this.weatherMenager = weatherMenager;
        activeStages = new ArrayList<Stage>();
    }


    private void initializeStage(BaseController baseController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;

        try {
            parent = fxmlLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);

    }

    public void showMainWindow() {

        System.out.println("show main window called");

        BaseController controller = new MainWindowController(weatherMenager, this,"/MainWindow.fxml");
        initializeStage(controller);

    }

}
