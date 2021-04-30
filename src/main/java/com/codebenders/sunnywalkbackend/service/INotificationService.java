package com.codebenders.sunnywalkbackend.service;

public interface INotificationService {
    void checkIfUsersNeedToBeNotified();
    String notifyUser(Integer userId, long time);
}
