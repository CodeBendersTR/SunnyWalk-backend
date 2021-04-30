package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.model.Location;

public interface ILocationService {
    Location getLocationFromName(String locationName);
}
