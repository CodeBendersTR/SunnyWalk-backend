package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.WalkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class WalkTest {

    @Autowired
    WalkRepository walkRepository;

    @BeforeEach
    void prerequisites() {

        Walk walk = new Walk();
        walk.setWalkId(1);
        walk.setLocationId(1);
        walk.setUserId(1);
        walk.setTime(new GregorianCalendar(2021, Calendar.APRIL, 25).getTime());
        walk.setDuration(35);
        walk.setDetails("Good walk");
        walk.setNotify(true);
        walk.setWeatherType("sunny");
        walk.setRating(5);

        walkRepository.save(walk);
    }

    @Test
    void getWalkId() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(1, walk.getWalkId());
    }

    @Test
    void getUserId() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(1, walk.getUserId());
    }

    @Test
    void getLocationId() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(1, walk.getLocationId());
    }

    @Test
    void getWeatherType() {
        Walk walk = walkRepository.getOne(1);
        assertEquals("sunny", walk.getWeatherType());
    }

    @Test
    void getTime() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(new GregorianCalendar(2021, Calendar.APRIL, 25).getTime(), walk.getTime());
    }

    @Test
    void getDuration() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(35, walk.getDuration());
    }

    @Test
    void getRating() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(5, walk.getRating());
    }

    @Test
    void getDetails() {
        Walk walk = walkRepository.getOne(1);
        assertEquals("Good walk", walk.getDetails());
    }

    @Test
    void getNotify() {
        Walk walk = walkRepository.getOne(1);
        assertEquals(true, walk.getNotify());
    }
}