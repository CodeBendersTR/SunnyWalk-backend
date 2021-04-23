package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.model.UserCredential;
import com.codebenders.sunnywalkbackend.model.UserPreference;
import com.codebenders.sunnywalkbackend.repository.UserCredentialRepository;
import com.codebenders.sunnywalkbackend.repository.UserPreferenceRepository;
import com.codebenders.sunnywalkbackend.repository.UserRepository;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserCredentialRepository userCredentialRepository;

  @Autowired
  IAuthService authService;

  @Autowired
  UserPreferenceRepository userPreferenceRepository;

  public Integer addUser(String email, String password, String firstName, String lastName){
    User user = userRepository.findUserByEmail(email);

    if(user != null){
      return null;
    }

    User newUser = new User();
    Random addRandom = new Random();

    Integer tempUserId = Math.abs(addRandom.nextInt());

    newUser.setUserId(tempUserId);
    newUser.setEmail(email);
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setDob(null);
    newUser.setGender(null);
    newUser.setLocationId(-1);
    newUser.setUserType(null);

    userRepository.save(newUser);

    UserCredential userCredential = new UserCredential();

    String passwordHash = hashString(tempUserId + password);

    userCredential.setUserId(tempUserId);
    userCredential.setPasswordHash(passwordHash);
    userCredential.setRole(null);

    userCredentialRepository.save(userCredential);

    UserPreference userPreference = new UserPreference();

    userPreference.setUserId(tempUserId);
    userPreference.setWeather(null);
    userPreference.setPushNotifications(null);
    userPreference.setMailNotifications(null);
    userPreference.setCookies(null);

    userPreferenceRepository.save(userPreference);
    return tempUserId;
  }

  // private methods
  private String hashString(String stringToHash) {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
    byte[] hashBytes = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
    return HexUtils.toHexString(hashBytes);
  }

  public Boolean userExists(String email){
    User user = userRepository.findUserByEmail(email);

    if(user == null) {
      return false;
    }
    return true;
  }

}
