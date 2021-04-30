package com.codebenders.sunnywalkbackend.service;

import com.codebenders.sunnywalkbackend.model.Location;
import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.model.UserCredential;
import com.codebenders.sunnywalkbackend.model.UserPreference;
import com.codebenders.sunnywalkbackend.repository.LocationRepository;
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

  @Autowired
  ILocationService locationService;

  @Autowired
  LocationRepository locationRepository;

  public Integer addUser(String email, String password, String firstName, String lastName, String city){
    Random random = new Random();

    // add location
    Location newLocation = locationService.getLocationFromName(city);
    newLocation.setLocationId(Math.abs(random.nextInt()));
    newLocation.setCheck(false);

    // add user
    User user = userRepository.findUserByEmail(email);

    if(user != null){
      return null;
    }

    User newUser = new User();

    newUser.setUserId(Math.abs(random.nextInt()));
    newUser.setEmail(email);
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setDob(null);
    newUser.setGender(null);
    newUser.setLocationId(newLocation.getLocationId());
    newUser.setUserType(null);

    locationRepository.save(newLocation);
    userRepository.save(newUser);

    // add password
    UserCredential userCredential = new UserCredential();

    String passwordHash = hashString(newUser.getUserId() + password);

    userCredential.setUserId(newUser.getUserId());
    userCredential.setPasswordHash(passwordHash);
    userCredential.setRole(null);

    userCredentialRepository.save(userCredential);

    // add preferences
    UserPreference userPreference = new UserPreference();

    userPreference.setUserId(newUser.getUserId());
    userPreference.setWeather(null);
    userPreference.setPushNotifications(null);
    userPreference.setMailNotifications(null);
    userPreference.setCookies(null);

    userPreferenceRepository.save(userPreference);


    return newUser.getUserId();
  }

  public String updateUser(int userId, String currentPassword, String newPassword, String location, String userType, String notification, String weather){
    // check for userId and passwordHash , if matches replace old pwdHash with new pwdHash
    if (!currentPassword.equals("") && !newPassword.equals("")) {
      UserCredential userCredential = userCredentialRepository.getByUserId(userId);
      String currentPasswordHash = hashString(userId + currentPassword);
      if (userCredential.getPasswordHash().equals(currentPasswordHash)) {
        String passwordHash = hashString(userId + newPassword);
        userCredential.setPasswordHash(passwordHash);
        userCredentialRepository.save(userCredential);
      }
    }

    UserPreference userPreference = userPreferenceRepository.getByUserId(userId);
    if(notification.equals("Email")){
      userPreference.setMailNotifications(true);
      userPreference.setPushNotifications(false);
    }

    if(notification.equals("Web Notification")){
      userPreference.setPushNotifications(true);
      userPreference.setMailNotifications(false);
    }

    userPreference.setWeather(weather);
    userPreferenceRepository.save(userPreference);

    // user type
    User user = userRepository.getOne(userId);
    user.setUserType(userType);
    userRepository.save(user);

    return "Success";
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
