package com.codebenders.sunnywalkbackend.service;

public interface IAuthService {
    String login(String email, String password);
    Boolean isUserLoggedIn(String sessionId);
    void logout(String sessionId);
}
