package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.model.UserCredential;
import com.codebenders.sunnywalkbackend.model.UserSession;
import com.codebenders.sunnywalkbackend.repository.UserCredentialRepository;
import com.codebenders.sunnywalkbackend.repository.UserRepository;
import com.codebenders.sunnywalkbackend.repository.UserSessionRepository;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    UserSessionRepository userSessionRepository;

    // public methods
    public String login(String email, String password) {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return null;
        }

        String passwordHash = hashString(user.getUserId() + password);
        UserCredential userCredential = userCredentialRepository.getByUserId(user.getUserId());

        if (! passwordHash.equals(userCredential.getPasswordHash())) {
            return null;
        }

        UserSession userSession = new UserSession();

        String sessionId = hashString(Long.toString(Double.doubleToLongBits(Math.random())));

        userSession.setUserId(user.getUserId());
        userSession.setSessionId(sessionId);
        userSession.setActive(true);
        userSession.setOpened(new Date());

        userSessionRepository.save(userSession);

        return sessionId;
    }

    public Boolean isUserLoggedIn(String sessionId) {
        Optional<UserSession> userSession = userSessionRepository.findById(sessionId);

        if (!userSession.isPresent()) {
            return false;
        }

        return userSession.get().getActive();
    }

    public void logout(String sessionId) {
        UserSession userSession = userSessionRepository.getOne(sessionId);
        userSession.setActive(false);
        userSessionRepository.save(userSession);
    }

    // private methods
    private String hashString(String stringToHash) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hashBytes = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
        return HexUtils.toHexString(hashBytes);
    }
}
