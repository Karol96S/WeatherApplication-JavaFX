package com.weather.model;

import com.weather.Config;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Connector {
    public static String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    public static String location="";
    public static String timeSpan ="next5days";
    public static String startDate=null; //optional (omit for forecast)
    public static String endDate=null; //optional (requires a startDate if present)
    public static String unitGroup="metric"; //us,metric,uk
    private static final String API_KEY = Config.getKey();
    public static String uri;
    public static int errorCode;
    private static Connector instance = new Connector();
    private Connector() {}

    public static Connector getInstance() {
        return instance;
    }

    public String getApiEndPoint() {
        return apiEndPoint;
    }

    public void setApiEndPoint(String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUnitGroup() {
        return unitGroup;
    }

    public void setUnitGroup(String unitGroup) {
        this.unitGroup = unitGroup;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static String buildApiRequest(String city) {
        location = city;
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
            return response.body();
            } else {
                errorCode = response.statusCode();
                return null;
            }
        } catch (IOException | InterruptedException e) {
            errorCode = 404;
            e.printStackTrace();
        }
        return null;
    }
}
