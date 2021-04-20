package com.codebenders.sunnywalkbackend.dto;

import java.util.ArrayList;

public class ApiWeatherForecastDailyDto {
    long dt;
    long sunrise;
    long sunset;
    long moonrise;
    long moonset;
    double moon_phase;
    ApiWeatherForecastTempDto temp;
    ApiWeatherForecastFeelsLikeDto feels_like;
    int pressure;
    int humidity;
    double dew_point;
    double wind_speed;
    int wind_deg;
    double wind_gust;
    ArrayList<ApiWeatherForecastDescriptionDto> weather;
    int clouds;
    int pop;
    double rain;
    double snow;
    double uvi;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public long getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(long moonrise) {
        this.moonrise = moonrise;
    }

    public long getMoonset() {
        return moonset;
    }

    public void setMoonset(long moonset) {
        this.moonset = moonset;
    }

    public double getMoon_phase() {
        return moon_phase;
    }

    public void setMoon_phase(double moon_phase) {
        this.moon_phase = moon_phase;
    }

    public ApiWeatherForecastTempDto getTemp() {
        return temp;
    }

    public void setTemp(ApiWeatherForecastTempDto temp) {
        this.temp = temp;
    }

    public ApiWeatherForecastFeelsLikeDto getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(ApiWeatherForecastFeelsLikeDto feels_like) {
        this.feels_like = feels_like;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getDew_point() {
        return dew_point;
    }

    public void setDew_point(double dew_point) {
        this.dew_point = dew_point;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

    public double getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(double wind_gust) {
        this.wind_gust = wind_gust;
    }

    public ArrayList<ApiWeatherForecastDescriptionDto> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<ApiWeatherForecastDescriptionDto> weather) {
        this.weather = weather;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public double getUvi() {
        return uvi;
    }

    public void setUvi(double uvi) {
        this.uvi = uvi;
    }
}
