package com.codebenders.sunnywalkbackend.service;


public interface IUserService {
    Integer addUser(String email, String password, String firstName, String lastName);
    Boolean userExists(String email);

}
