package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.dto.AddWalkDto;
import com.codebenders.sunnywalkbackend.dto.WalkSuggestionDto;
import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.repository.UserRepository;
import com.codebenders.sunnywalkbackend.repository.UserSessionRepository;
import com.codebenders.sunnywalkbackend.service.IAuthService;
import com.codebenders.sunnywalkbackend.service.IWalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/walk")
public class WalkController {

    @Autowired
    IWalkService walkService;

    @Autowired
    IAuthService authService;

    @Autowired
    UserSessionRepository userSessionRepository;

    @GetMapping("/suggestion")
    public ResponseEntity<ArrayList<WalkSuggestionDto>> getWalkSuggestion(@RequestParam(required = false) String sessionId) {
        if (sessionId == null || !authService.isUserLoggedIn(sessionId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        int userId = userSessionRepository.getOne(sessionId).getUserId();
        return ResponseEntity.status(HttpStatus.OK).body(walkService.getSuggestion(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWalk(@RequestBody AddWalkDto addWalkDto, @RequestParam(required = false) String sessionId) {
        if (sessionId == null || !authService.isUserLoggedIn(sessionId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
        }
        int userId = userSessionRepository.getOne(sessionId).getUserId();
        walkService.addWalk(userId, addWalkDto);
        return new ResponseEntity<String>("Walk added successfully", HttpStatus.OK);
    }


}
