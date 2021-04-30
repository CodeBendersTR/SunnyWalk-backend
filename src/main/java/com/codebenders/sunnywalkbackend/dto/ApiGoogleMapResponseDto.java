package com.codebenders.sunnywalkbackend.dto;

import java.util.ArrayList;

public class ApiGoogleMapResponseDto {
    ArrayList<ApiGoogleMapPlaceDto> candidates;
    String status;

    public ArrayList<ApiGoogleMapPlaceDto> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<ApiGoogleMapPlaceDto> candidates) {
        this.candidates = candidates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
