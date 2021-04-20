package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.dto.ApiWeatherForecastHourlyDto;
import com.codebenders.sunnywalkbackend.dto.WalkSuggestionDto;
import com.codebenders.sunnywalkbackend.model.Location;

import java.util.ArrayList;

public interface IWeatherService {
    ArrayList<ApiWeatherForecastHourlyDto> getWeatherForecast(Location location);
}
