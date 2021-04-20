package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.dto.ApiWeatherForecastHourlyDto;
import com.codebenders.sunnywalkbackend.dto.WalkSuggestionDto;
import com.codebenders.sunnywalkbackend.model.Location;
import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.repository.LocationRepository;
import com.codebenders.sunnywalkbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Service
public class WalkService implements IWalkService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    IWeatherService weatherService;

    @Override
    public ArrayList<WalkSuggestionDto> getSuggestion(int userId) {
        User user = userRepository.getOne(userId);
        Location location = locationRepository.getOne(user.getLocationId());
        ArrayList<ApiWeatherForecastHourlyDto> hourlyForecast = weatherService.getWeatherForecast(location);
        hourlyForecast.remove(0); // remove first element (previous hour)

        ArrayList<ApiWeatherForecastHourlyDto> weatherSuggestions = new ArrayList<>();

        // separate the hours for today and tomorrow
        long midnightToday = getMidnight(0);
        long midnightTomorrow = getMidnight(1);

        ArrayList<ApiWeatherForecastHourlyDto> todayHourlyForecast = new ArrayList<>();
        ArrayList<ApiWeatherForecastHourlyDto> tomorrowHourlyForecast = new ArrayList<>();

        for (ApiWeatherForecastHourlyDto hourForecast : hourlyForecast) {
            if (hourForecast.getDt() < midnightToday) {
                todayHourlyForecast.add(hourForecast);
            } else if (hourForecast.getDt() < midnightTomorrow) {
                tomorrowHourlyForecast.add(hourForecast);
            }
        }

        // get suggestions
        weatherSuggestions.addAll(filterWeatherSuggestions(todayHourlyForecast, 3));

        if (weatherSuggestions.size() < 3) {
            weatherSuggestions.addAll(filterWeatherSuggestions(tomorrowHourlyForecast, 3 - weatherSuggestions.size()));
        }

        // create the walk dto array
        ArrayList<WalkSuggestionDto> walkSuggestions = new ArrayList<>();
        Collections.sort(weatherSuggestions, Comparator.comparingDouble(ApiWeatherForecastHourlyDto ::getDt));
        for (ApiWeatherForecastHourlyDto weatherSuggestion : weatherSuggestions) {
            WalkSuggestionDto walkSuggestion = new WalkSuggestionDto();

            walkSuggestion.setTime(weatherSuggestion.getDt());
            walkSuggestion.setTemp((int) Math.round(weatherSuggestion.getTemp()));
            walkSuggestion.setUvi(weatherSuggestion.getUvi());
            walkSuggestion.setWeatherDescription(weatherSuggestion.getWeather().get(0).getDescription());
            walkSuggestion.setLocation(location.getLocationName());

            walkSuggestions.add(walkSuggestion);
        }

        return walkSuggestions;
    }

    private long getMidnight(int day) {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/London"));
        LocalDateTime midnight = LocalDateTime.of(today, LocalTime.MIDNIGHT);
        LocalDateTime dayMidnight = midnight.plusDays(day + 1);
        return dayMidnight.toEpochSecond(ZoneOffset.ofHours(0));
    }

    private ArrayList<ApiWeatherForecastHourlyDto> filterWeatherSuggestions(ArrayList<ApiWeatherForecastHourlyDto> hourlyForecast, int howMany) {
        ArrayList<ApiWeatherForecastHourlyDto> weatherSuggestions = new ArrayList<>();

        Collections.sort(hourlyForecast, Comparator.comparingDouble(ApiWeatherForecastHourlyDto ::getUvi));
        for (int i = hourlyForecast.size() - 1; i >= 0; i--) {
            if (hourlyForecast.get(i).getUvi() <= 5 && hourlyForecast.get(i).getUvi() > 0) {
                weatherSuggestions.add(hourlyForecast.get(i));

                if (weatherSuggestions.size() >= howMany) {
                    break;
                }
            }
        }

        return weatherSuggestions;
    }
}
