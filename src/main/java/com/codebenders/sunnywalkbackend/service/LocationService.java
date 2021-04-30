package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.dto.ApiGoogleMapPlaceDto;
import com.codebenders.sunnywalkbackend.dto.ApiGoogleMapResponseDto;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LocationService implements ILocationService {

    @Autowired
    ApiKeyRepository apiKeyRepository;

    @Override
    public Location getLocationFromName(String city) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> googleMapsResponse = restTemplate.getForEntity(getGoogleMapsUri(city), String.class);
        ApiGoogleMapResponseDto googleMapResponseDto = null;
        try {
            googleMapResponseDto = mapper.readValue(googleMapsResponse.getBody(), ApiGoogleMapResponseDto.class);
        } catch (Exception e) {
            // nothing to do
        }

        if (googleMapResponseDto == null || !googleMapResponseDto.getStatus().equals("OK")) {
            try {
                googleMapResponseDto = mapper.readValue(googleMapsResponse.getBody(), ApiGoogleMapResponseDto.class);
            } catch (Exception e) {
                // nothing to do
            }
        }

        Location location = new Location();
        if (googleMapResponseDto == null || !googleMapResponseDto.getStatus().equals("OK")) {
            location.setLocationName("London");
            location.setLatitude("51.501388");
            location.setLongitude("-0.141996");
        } else {
            ApiGoogleMapPlaceDto googleMapPlaceDto = googleMapResponseDto.getCandidates().get(0);
            location.setLocationName(getFirstLocationName(googleMapPlaceDto.getFormatted_address()));
            location.setLatitude(truncateCoordinateAndMakeItString(googleMapPlaceDto.getGeometry().getLocation().getLat()));
            location.setLongitude(truncateCoordinateAndMakeItString(googleMapPlaceDto.getGeometry().getLocation().getLng()));
        }

        return location;
    }

    private String getGoogleMapsUri(String locationName) {
        String googleMapsApiUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";

        ApiKey googleMapsApiKey = apiKeyRepository.getOne("google_maps");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(googleMapsApiUrl)
                .queryParam("input", locationName)
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "formatted_address,geometry/location")
                .queryParam("key", googleMapsApiKey.getApiKey());
        return builder.toUriString();
    }

    private String getFirstLocationName(String bigLocationName) {

        Pattern regex = Pattern.compile("(.+?)(?:,|$)");
        Matcher match = regex.matcher(bigLocationName);
        if (match.find()) {
            return match.group(1);
        } else {
            return bigLocationName;
        }
    }

    private String truncateCoordinateAndMakeItString(Double coordinate) {
        Pattern regex = Pattern.compile("(-?\\d+\\.\\d{1,6})");
        Matcher match = regex.matcher(coordinate.toString());

        if (match.find()) {
            return match.group(0);
        } else {
            return coordinate.toString();
        }
    }
}
