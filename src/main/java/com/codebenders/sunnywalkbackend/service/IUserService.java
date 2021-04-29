package com.codebenders.sunnywalkbackend.service;


public interface IUserService {
    Integer addUser(String email, String password, String firstName, String lastName);
    String updateUser(String email, String currentPassword, String newPassword, String location, String userType, String notification, String weather);
    Boolean userExists(String email);

}
