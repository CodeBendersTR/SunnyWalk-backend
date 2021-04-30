package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.dto.AddWalkDto;
import com.codebenders.sunnywalkbackend.dto.NotifyDto;
import com.codebenders.sunnywalkbackend.repository.UserSessionRepository;
import com.codebenders.sunnywalkbackend.repository.WalkRepository;
import com.codebenders.sunnywalkbackend.service.IAuthService;
import com.codebenders.sunnywalkbackend.service.IEmailService;
import com.codebenders.sunnywalkbackend.service.INotificationService;
import com.codebenders.sunnywalkbackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@EnableScheduling
@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    INotificationService notificationService;

    @Autowired
    IAuthService authService;

    @Scheduled(fixedRate = 60000)
    public void sendEmail() {
        notificationService.checkIfUsersNeedToBeNotified();
    }

    @Autowired
    UserSessionRepository userSessionRepository;

    @PutMapping("/walk")
    public ResponseEntity<String> notifyUser(@RequestBody NotifyDto notifyDto, @RequestParam(required = false) String sessionId) {
      System.out.println(sessionId);
      if (sessionId == null || !authService.isUserLoggedIn(sessionId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
      }
      Integer userId = userSessionRepository.getOne(sessionId).getUserId();
      String response = notificationService.notifyUser(userId, notifyDto.getNotify());

      return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
