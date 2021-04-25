package com.codebenders.sunnywalkbackend.model;

import com.codebenders.sunnywalkbackend.repository.ApiKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ApiKeyTest {

    @Autowired
    ApiKeyRepository apiKeyRepository;

    @BeforeEach
    void prerequisites() {
        ApiKey newApiKey = new ApiKey();
        newApiKey.setApiKey("key");
        newApiKey.setService("serv");
        apiKeyRepository.save(newApiKey);
    }

    @Test
    void getServiceTest() {
        ApiKey apiKey = apiKeyRepository.getOne("serv");
        assertEquals(apiKey.getService(), "serv");
    }

    @Test
    void getApiKeyTest() {
        ApiKey apiKey = apiKeyRepository.getOne("serv");
        assertEquals(apiKey.getApiKey(), "key");
    }
}