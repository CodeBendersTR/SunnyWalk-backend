package com.codebenders.sunnywalkbackend.dto;

public class ApiGoogleMapPlaceDto {
    String formatted_address;
    ApiGoogleMapGeometryDto geometry;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public ApiGoogleMapGeometryDto getGeometry() {
        return geometry;
    }

    public void setGeometry(ApiGoogleMapGeometryDto geometry) {
        this.geometry = geometry;
    }
}
