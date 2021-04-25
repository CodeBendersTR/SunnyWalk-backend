package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class LocationTest {

    @Autowired
    LocationRepository locationRepository;

    @BeforeEach
    void prerequisites() {

        Location location = new Location();
        location.setLocationId(1);
        location.setLocationName("London");
        location.setCheck(false);
        location.setLatitude("51.50853");
        location.setLongitude("-0.12574");

        locationRepository.save(location);
    }

    @Test
    void getLocationId() {
        Location location = locationRepository.getOne(1);
        assertEquals(1, location.getLocationId());
    }

    @Test
    void getLocationName() {
        Location location = locationRepository.getOne(1);
        assertEquals("London", location.getLocationName());
    }

    @Test
    void getLatitude() {
        Location location = locationRepository.getOne(1);
        assertEquals("51.50853", location.getLatitude());
    }

    @Test
    void getLongitude() {
        Location location = locationRepository.getOne(1);
        assertEquals("-0.12574", location.getLongitude());
    }

    @Test
    void getCheck() {
        Location location = locationRepository.getOne(1);
        assertEquals(false, location.getCheck());
    }
}