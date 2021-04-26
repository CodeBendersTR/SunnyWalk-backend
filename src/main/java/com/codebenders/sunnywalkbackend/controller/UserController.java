package com.codebenders.sunnywalkbackend.controller;

import com.codebenders.sunnywalkbackend.dto.ProfileDto;
import com.codebenders.sunnywalkbackend.dto.RegisterDto;
import com.codebenders.sunnywalkbackend.service.IUserService;
import com.codebenders.sunnywalkbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  IUserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
    Integer userId = userService.addUser(registerDto.getEmail(), registerDto.getPassword(), registerDto.getFirstName(), registerDto.getLastName());

    if(userId == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }
    return ResponseEntity.status(HttpStatus.OK).body(userId.toString());

  }

  @PutMapping("/profile/{userId}")
  public ResponseEntity<String> profile(@RequestBody ProfileDto profileDto){
    String userName = userService.updateUser(profileDto.getEmail(), profileDto.getCurrentPassword(), profileDto.getNewPassword(), profileDto.getLocation(), profileDto.getUserType(),
      profileDto.getNotification(), profileDto.getWeather());

    return ResponseEntity.status(HttpStatus.OK).body("profile found");

  }

}
