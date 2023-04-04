package com.weather.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WeatherData {
    public  List<Double> cityMaxTemperatures = new ArrayList<Double>();
    public  List<Double> cityMinTemperatures = new ArrayList<Double>();
    public  List<String> iconType = new ArrayList<String>();
    public  List<ZonedDateTime> dates = new ArrayList<ZonedDateTime>();
    public List<DayOfWeek> days = new ArrayList<DayOfWeek>();


    public List<Double> getCityMaxTemperatures() {
        return cityMaxTemperatures;
    }

    public List<Double> getCityMinTemperatures() {
        return cityMinTemperatures;
    }

    public List<String> getIconTypes() {
        return iconType;
    }

    public List<ZonedDateTime> getDates() {
        return dates;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public WeatherData(String rawData) {
        //String rawData = sendRequest();
        JSONObject timelineResponse = new JSONObject(rawData);
        ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));
        System.out.printf("Weather data for: %s%n", timelineResponse.getString("resolvedAddress"));

        JSONArray values=timelineResponse.getJSONArray("days");

        System.out.printf("Date\tMaxTemp\tMinTemp\tPrecip\tIcon%n");
        for (int i = 0; i < values.length(); i++) {
            JSONObject dayValue = values.getJSONObject(i);

            ZonedDateTime datetime=
                    ZonedDateTime.ofInstant(Instant.ofEpochSecond(
                            dayValue.getLong("datetimeEpoch")), zoneId);

            double maxTemp=dayValue.getDouble("tempmax");
            double minTemp=dayValue.getDouble("tempmin");
            double pop=dayValue.getDouble("precip");
            String icon=dayValue.getString("icon");
            System.out.printf("%s\t%.1f\t%.1f\t%.1f\t%s%n",
                    datetime.format(DateTimeFormatter.ISO_LOCAL_DATE),
                    maxTemp, minTemp, pop, icon );
            this.dates.add(datetime);
            this.days.add(datetime.getDayOfWeek());
            this.cityMaxTemperatures.add(maxTemp);
            this.cityMinTemperatures.add(minTemp);
            this.iconType.add(icon);
        }
    }

}
