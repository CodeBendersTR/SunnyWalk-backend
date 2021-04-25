package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.UserPreferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserPreferenceTest {

    @Autowired
    UserPreferenceRepository userPreferenceRepository;

    @BeforeEach
    void prerequisites() {

        UserPreference userPreference = new UserPreference();
        userPreference.setUserId(1);
        userPreference.setCookies(true);
        userPreference.setMailNotifications(true);
        userPreference.setPushNotifications(false);
        userPreference.setWeather("sunny");

        userPreferenceRepository.save(userPreference);
    }

    @Test
    void getUserId() {
        UserPreference userPreference = userPreferenceRepository.getOne(1);
        assertEquals(1, userPreference.getUserId());
    }

    @Test
    void getWeather() {
        UserPreference userPreference = userPreferenceRepository.getOne(1);
        assertEquals("sunny", userPreference.getWeather());
    }

    @Test
    void getPushNotifications() {
        UserPreference userPreference = userPreferenceRepository.getOne(1);
        assertEquals(false, userPreference.getPushNotifications());
    }

    @Test
    void getMailNotifications() {
        UserPreference userPreference = userPreferenceRepository.getOne(1);
        assertEquals(true, userPreference.getMailNotifications());
    }

    @Test
    void getCookies() {
        UserPreference userPreference = userPreferenceRepository.getOne(1);
        assertEquals(true, userPreference.getCookies());
    }
}