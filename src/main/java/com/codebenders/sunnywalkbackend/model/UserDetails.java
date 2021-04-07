package com.codebenders.sunnywalkbackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_detail")
public class UserDetails {
  @Id
  @Column(name="user_id")
  Integer userId;

  @Column(name="gender")
  String gender;

  @Column(name="dob")
  Date dob;

  @Column(name="location_id")
  Integer locationId;

  @Column(name="walker_type")
  String walkerType;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }

  public String getWalkerType() {
    return walkerType;
  }

  public void setWalkerType(String walkerType) {
    this.walkerType = walkerType;
  }



}
