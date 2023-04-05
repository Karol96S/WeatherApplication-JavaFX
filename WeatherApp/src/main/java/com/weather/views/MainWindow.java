package com.weather.views;

import com.weather.controller.BaseController;
import com.weather.controller.MainWindowController;
import com.weather.model.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {

    private ArrayList<Stage> activeStages;
    private final int iconHeight = 55;
    private final int iconWidth = 55;

    public MainWindow() {
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
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        Stage stage = new Stage();
        stage.setTitle("Aplikacja Pogodowa");
        stage.setScene(scene);
        stage.show();

    }

    public void showMainWindow() {

        System.out.println("show main window called");

        BaseController controller = new MainWindowController(this,"/MainWindow.fxml");
        initializeStage(controller);

    }

    public void populateDaysOfTheWeek(List<ZonedDateTime> calendarDates, List<DayOfWeek> dayOfWeeks, GridPane weatherGrid) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            Label labelDay = new Label();
            Label labelDate = new Label();
            String day = Date.translatedDayOfTheWeek(dayOfWeeks.get(row));
            String date = calendarDates.get(row).format(DateTimeFormatter.ofPattern("dd.MM.YYYY")).toString();
            labelDay.setText(day);
            labelDay.setStyle("-fx-font-weight: bold");
            labelDate.setText(date);
            VBox vBox = new VBox();
            vBox.getChildren().add(labelDay);
            vBox.getChildren().add(labelDate);
            weatherGrid.add(vBox, 0, row);
        }
    }

    public void populateStartCityIcons(List<String> icons, GridPane weatherGrid) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            ImageView imageView = new ImageView();
            String icon = icons.get(row);
            String imagePath = getIconPath(icon);
            System.out.println(imagePath);
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
            imageView.setFitHeight(iconHeight);
            imageView.setFitWidth(iconWidth);
            weatherGrid.add(imageView, 1, row);
        }
    }

    public void populateStartCityTemperatures(List<Double> dayTemperatures, List<Double> nightTemperatures, GridPane weatherGrid) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            Label labelDayStartTemp = new Label();
            String text = "Dzień: " + dayTemperatures.get(row).toString() + "\u00B0" + "C";
            labelDayStartTemp.setText(text);
            labelDayStartTemp.setMaxWidth(Double.MAX_VALUE);
            labelDayStartTemp.setFont(new Font(12));
            Label labelNightStartTemp = new Label();

            text = "Noc: " + nightTemperatures.get(row).toString() + "\u00B0" + "C";
            labelNightStartTemp.setText(text);
            labelNightStartTemp.setMaxWidth(Double.MAX_VALUE);
            labelNightStartTemp.setFont(new Font(12));
            VBox vBox = new VBox();
            vBox.getChildren().add(labelDayStartTemp);
            vBox.getChildren().add(labelNightStartTemp);
            weatherGrid.add(vBox,2,row);
        }
    }

    public void populateDestinationCityIcons(List<String> icons, GridPane weatherGrid) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            ImageView imageView = new ImageView();
            String icon = icons.get(row);
            String imagePath = getIconPath(icon);
            System.out.println(imagePath);
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
            imageView.setFitHeight(iconHeight);
            imageView.setFitWidth(iconWidth);
            weatherGrid.add(imageView, 4, row);
        }
    }

    public void populateDestinationCityTemperatures(List<Double> dayTemperatures, List<Double> nightTemperatures, GridPane weatherGrid) {
        for(int row = 0; row < weatherGrid.getRowCount(); row++) {
            Label labelDayStartTemp = new Label();
            String text = "Dzień: " + dayTemperatures.get(row).toString() + "\u00B0" + "C";
            labelDayStartTemp.setText(text);
            labelDayStartTemp.setMaxWidth(Double.MAX_VALUE);
            labelDayStartTemp.setFont(new Font(12));
            Label labelNightStartTemp = new Label();

            text = "Noc: " + nightTemperatures.get(row).toString() + "\u00B0" + "C";
            labelNightStartTemp.setText(text);
            labelNightStartTemp.setMaxWidth(Double.MAX_VALUE);
            labelNightStartTemp.setFont(new Font(12));
            VBox vBox = new VBox();
            vBox.getChildren().add(labelDayStartTemp);
            vBox.getChildren().add(labelNightStartTemp);
            weatherGrid.add(vBox,5,row);
        }
    }

    private static String getIconPath(String icon) {

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
            case "thunder":
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
                imagePath = null;
                break;
        }
        return imagePath;
    }

}
