package com.weather.model.client;

import com.weather.Config;
import com.weather.model.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class VisualCrossingClient implements WeatherClient {

    public static String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    public static String location="";
    public static String timeSpan ="next5days";
    public static String startDate=null; //optional (omit for forecast)
    public static String endDate=null; //optional (requires a startDate if present)
    public static String unitGroup="metric"; //us,metric,uk
    private static final String API_KEY = Config.getKey();
    public static String uri;
    public static int statusCode;

    @Override
    public WeatherData getWeather(String cityName) {
        this.uri = buildApiRequest(cityName);
        String rawData = sendRequest(uri);

        return new WeatherData(rawData, statusCode);
    }

    public static String buildApiRequest(String city) {
        String location = city.replaceAll("\\s+","");
        StringBuilder requestBuilder=new StringBuilder(apiEndPoint);
        requestBuilder.append(location);

        if (startDate!=null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate!=null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }

        requestBuilder.append("/").append(timeSpan);
        requestBuilder.append("?unitGroup=").append(unitGroup);
        requestBuilder.append("&key=").append(API_KEY);
        uri = requestBuilder.toString();
        return uri;
    }

    public static String sendRequest(String uri) {
        System.out.println(uri);

        var uriString = URI.create(uri);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uriString)
                .header("Accept", "application/json")
                .build();
        try {
            var response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
            );

            System.out.println(response.statusCode());
            System.out.println(response.body());

            if(response.statusCode() == 200) {
                statusCode = 200;
                return response.body();
            } else {
                statusCode = response.statusCode();
                return null;
            }
        } catch (IOException | InterruptedException e) {
            statusCode = 404;
            e.printStackTrace();
        }
        return null;
    }
}
