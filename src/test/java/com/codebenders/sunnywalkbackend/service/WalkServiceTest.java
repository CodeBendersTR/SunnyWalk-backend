package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.model.ApiKey;
import com.codebenders.sunnywalkbackend.model.Location;
import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.repository.ApiKeyRepository;
import com.codebenders.sunnywalkbackend.repository.LocationRepository;
import com.codebenders.sunnywalkbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class WalkServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ApiKeyRepository apiKeyRepository;

    @Autowired
    IWalkService walkService;

    @BeforeEach
    void prerequisites() {
        User user = new User();
        user.setUserId(1);
        user.setDob(new Date());
        user.setEmail("john.doe@gmail.com");
        user.setUserType("USER");
        user.setGender("Male");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setLocationId(1);
        userRepository.save(user);

        Location location = new Location();
        location.setLocationId(1);
        location.setLocationName("London");
        location.setCheck(false);
        location.setLatitude("51.50853");
        location.setLongitude("-0.12574");
        locationRepository.save(location);

        ApiKey apiKey = new ApiKey();
        apiKey.setService("open_weather");
        apiKey.setApiKey("b7bb18e1c75c732d324ecc4e882e9f9d");
        apiKeyRepository.save(apiKey);
    }

    @Test
    void getSuggestion() {
        assertTrue(walkService.getSuggestion(1).size() <= 3);
    }
}