package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.UserCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserCredentialTest {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @BeforeEach
    void prerequisites() {

        UserCredential userCredential = new UserCredential();
        userCredential.setUserId(1);
        userCredential.setPasswordHash("password_hash");
        userCredential.setRole("USER");

        userCredentialRepository.save(userCredential);
    }

    @Test
    void getUserId() {
        UserCredential userCredential = userCredentialRepository.getByUserId(1);
        assertEquals(1, userCredential.getUserId());
    }

    @Test
    void getPasswordHash() {
        UserCredential userCredential = userCredentialRepository.getByUserId(1);
        assertEquals("password_hash", userCredential.getPasswordHash());
    }

    @Test
    void getRole() {
        UserCredential userCredential = userCredentialRepository.getByUserId(1);
        assertEquals("USER", userCredential.getRole());
    }
}