package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.dto.LoginDto;
import com.codebenders.sunnywalkbackend.service.IAuthService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) throws LoginException {
        String sessionId = authService.login(loginDto.getEmail(), loginDto.getPassword());

        if (sessionId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password is incorrect");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(sessionId);
        }
    }

    @PutMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam(required = false) String sessionId) {
        if (sessionId == null || !authService.isUserLoggedIn(sessionId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not logged in");
        }
        authService.logout(sessionId);
        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully");
    }
}
