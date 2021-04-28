package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    IEmailService emailService;

    @Scheduled(fixedRate = 60000)
    @GetMapping("/email")
    public void sendEmail() {
        emailService.sendEmail("Take your sunny walk in 10 minutes", "mariusgrygore@gmail.com");
    }
}
