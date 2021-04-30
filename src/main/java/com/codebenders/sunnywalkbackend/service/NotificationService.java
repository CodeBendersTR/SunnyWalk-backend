package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.dto.NotifyDto;
import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.model.Walk;
import com.codebenders.sunnywalkbackend.repository.UserRepository;
import com.codebenders.sunnywalkbackend.repository.WalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class NotificationService implements INotificationService {

    @Autowired
    IEmailService emailService;

    @Autowired
    WalkRepository walkRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void checkIfUsersNeedToBeNotified() {
        ArrayList<Walk> walksThatNeedToBeNotified = walkRepository.getWalkByNotify(true);
        int reminderTime = 30; // minutes

        for (Walk walk : walksThatNeedToBeNotified) {
            long minutesDiff = TimeUnit.MILLISECONDS.toMinutes(walk.getTime().getTime() - new Date().getTime());

            if (minutesDiff < 0) {
                walk.setNotify(false);
            } else if (minutesDiff <= reminderTime) {
                User user = userRepository.getOne(walk.getUserId());
                emailService.sendEmail("Hey " + user.getFirstName() + "! Get your sunny walk in " + minutesDiff + " minutes", user.getEmail());
                walk.setNotify(false);
            } else {
                continue;
            }
            walkRepository.save(walk);
        }
    }

    public String notifyUser(Integer userId, long time) {
        User user = userRepository.getOne(userId);

        Walk walk = new Walk();
        walk.setWalkId(Math.abs(new Random().nextInt()));
        walk.setUserId(userId);
        walk.setNotify(true);
        walk.setLocationId(user.getLocationId());
        walk.setTime(new Date(time * 1000));
        walkRepository.save(walk);

        return "User will be notified";
    }
}
