package com.weather.model;

import com.weather.Config;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Connector {
    public static String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    public static String location="";
    public static String timeSpan ="next5days";
    public static String startDate=null; //optional (omit for forecast)
    public static String endDate=null; //optional (requires a startDate if present)
    public static String unitGroup="metric"; //us,metric,uk
    private static String API_KEY = Config.getKey();
    public static String uri;
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

    public static String sendRequest(String uri) throws IOException, InterruptedException {
        //buildApiRequest();
        System.out.println(uri);
        var uriString = URI.create(uri);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uriString)
                .header("Accept", "application/json")
                .build();
        var response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );
        System.out.println(response.body());
        return response.body();
    }
}
