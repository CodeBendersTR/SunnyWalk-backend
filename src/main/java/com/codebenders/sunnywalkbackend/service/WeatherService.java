package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.dto.ApiWeatherForecastDto;
import com.codebenders.sunnywalkbackend.dto.ApiWeatherForecastHourlyDto;
import com.codebenders.sunnywalkbackend.model.ApiKey;
import com.codebenders.sunnywalkbackend.model.Location;
import com.codebenders.sunnywalkbackend.repository.ApiKeyRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Service
public class WeatherService implements IWeatherService {

    @Autowired
    ApiKeyRepository apiKeyRepository;

    RestTemplate restTemplate = new RestTemplate();

    private ApiWeatherForecastDto callWeatherApi(String lat, String lon) {
        String weatherApiUrl = "https://api.openweathermap.org/data/2.5/onecall";

        ApiKey weatherApiKey = apiKeyRepository.getOne("open_weather");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "metric")
                .queryParam("appid", weatherApiKey.getApiKey());


        ResponseEntity<String> weatherResponse = restTemplate.getForEntity(builder.toUriString(), String.class);

        ApiWeatherForecastDto weatherForecast;

        try {
            return mapper.readValue(weatherResponse.getBody(), ApiWeatherForecastDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<ApiWeatherForecastHourlyDto> getWeatherForecast(Location location) {
        ApiWeatherForecastDto weatherForecast = callWeatherApi(location.getLatitude(), location.getLongitude());
        return weatherForecast.getHourly();
    }
}
