package com.codebenders.sunnywalkbackend.dto;

public class ApiGoogleMapGeometryDto {
    ApiGoogleMapLocationDto location;

    public ApiGoogleMapLocationDto getLocation() {
        return location;
    }

    public void setLocation(ApiGoogleMapLocationDto location) {
        this.location = location;
    }
}
