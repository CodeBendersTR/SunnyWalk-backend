package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class UserTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void prerequisites() {

        User user = new User();
        user.setUserId(1);
        user.setUserType("walker");
        user.setLocationId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail("john.smith@gmail.com");
        user.setGender("male");
        user.setDob(new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime());

        userRepository.save(user);
    }

    @Test
    void getUserId() {
        User user = userRepository.getOne(1);
        assertEquals(1, user.getUserId());
    }

    @Test
    void getEmail() {
        User user = userRepository.getOne(1);
        assertEquals("john.smith@gmail.com", user.getEmail());
    }

    @Test
    void getFirstName() {
        User user = userRepository.getOne(1);
        assertEquals("John", user.getFirstName());
    }

    @Test
    void getLastName() {
        User user = userRepository.getOne(1);
        assertEquals("Smith", user.getLastName());
    }

    @Test
    void getGender() {
        User user = userRepository.getOne(1);
        assertEquals("male", user.getGender());
    }

    @Test
    void getDob() {
        User user = userRepository.getOne(1);
        assertEquals(new GregorianCalendar(1999, Calendar.JANUARY, 1).getTime(), user.getDob());
    }

    @Test
    void getLocationId() {
        User user = userRepository.getOne(1);
        assertEquals(1, user.getLocationId());
    }

    @Test
    void getUserType() {
        User user = userRepository.getOne(1);
        assertEquals("walker", user.getUserType());
    }
}