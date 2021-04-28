package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.service.IAuthService;
import com.codebenders.sunnywalkbackend.service.IEmailService;
import com.codebenders.sunnywalkbackend.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
public class NotificationController {

    @Autowired
    INotificationService notificationService;

    @Autowired
    IAuthService authService;

    @Scheduled(fixedRate = 60000)
    public void sendEmail() {
        notificationService.checkIfUsersNeedToBeNotified();
    }
}
