package com.codebenders.sunnywalkbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "user_preference")
public class UserPreference {
    @Id
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "weather")
    String weather;

    @Column(name = "push_notifications")
    Boolean pushNotifications;

    @Column(name = "mail_notifications")
    Boolean mailNotifications;

    @Column(name = "cookies")
    Boolean cookies;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Boolean getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(Boolean pushNotifications) {
        this.pushNotifications = pushNotifications;
    }

    public Boolean getMailNotifications() {
        return mailNotifications;
    }

    public void setMailNotifications(Boolean mailNotifications) {
        this.mailNotifications = mailNotifications;
    }

    public Boolean getCookies() {
        return cookies;
    }

    public void setCookies(Boolean cookies) {
        this.cookies = cookies;
    }
}
