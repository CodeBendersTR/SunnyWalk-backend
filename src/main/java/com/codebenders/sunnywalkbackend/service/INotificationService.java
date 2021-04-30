package com.codebenders.sunnywalkbackend.service;

public interface INotificationService {
    void checkIfUsersNeedToBeNotified();
    String notifyUser(Integer userId, Boolean notify);
}
