package com.codebenders.sunnywalkbackend.dto;

public class ApiWeatherForecastMinutelyDto {
    long dt;
    int precipitation;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }
}
