package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.UserSessionRepository;
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
class UserSessionTest {

    @Autowired
    UserSessionRepository userSessionRepository;

    @BeforeEach
    void prerequisites() {

        UserSession userSession = new UserSession();
        userSession.setUserId(1);
        userSession.setSessionId("MySessionId");
        userSession.setActive(true);
        userSession.setOpened(new GregorianCalendar(2021, Calendar.APRIL, 25).getTime());

        userSessionRepository.save(userSession);
    }

    @Test
    void getSessionId() {
        UserSession userSession = userSessionRepository.getByUserId(1);
        assertEquals("MySessionId", userSession.getSessionId());
    }

    @Test
    void getUserId() {
        UserSession userSession = userSessionRepository.getByUserId(1);
        assertEquals(1, userSession.getUserId());
    }

    @Test
    void getOpened() {
        UserSession userSession = userSessionRepository.getByUserId(1);
        assertEquals(true, userSession.getActive());
    }

    @Test
    void getActive() {
        UserSession userSession = userSessionRepository.getByUserId(1);
        assertEquals(new GregorianCalendar(2021, Calendar.APRIL, 25).getTime(), userSession.getOpened());
    }
}