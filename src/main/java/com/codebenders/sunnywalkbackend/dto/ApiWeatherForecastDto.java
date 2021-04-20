package com.codebenders.sunnywalkbackend.dto;

import java.util.ArrayList;

public class ApiWeatherForecastDto {
    String lat;
    String lon;
    String timezone;
    int timezone_offset;
    ApiWeatherForecastCurrentDto current;
    ArrayList<ApiWeatherForecastMinutelyDto> minutely;
    ArrayList<ApiWeatherForecastHourlyDto> hourly;
    ArrayList<ApiWeatherForecastDailyDto> daily;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public ApiWeatherForecastCurrentDto getCurrent() {
        return current;
    }

    public void setCurrent(ApiWeatherForecastCurrentDto current) {
        this.current = current;
    }

    public ArrayList<ApiWeatherForecastMinutelyDto> getMinutely() {
        return minutely;
    }

    public void setMinutely(ArrayList<ApiWeatherForecastMinutelyDto> minutely) {
        this.minutely = minutely;
    }

    public ArrayList<ApiWeatherForecastHourlyDto> getHourly() {
        return hourly;
    }

    public void setHourly(ArrayList<ApiWeatherForecastHourlyDto> hourly) {
        this.hourly = hourly;
    }

    public ArrayList<ApiWeatherForecastDailyDto> getDaily() {
        return daily;
    }

    public void setDaily(ArrayList<ApiWeatherForecastDailyDto> daily) {
        this.daily = daily;
    }
}
