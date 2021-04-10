package com.codebenders.sunnywalkbackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "walk")
public class Walk {
    @Id
    @Column (name = "walk_id")
    int walkId;

    @Column (name = "user_id")
    int userId;

    @Column (name = "location_id")
    int locationId;

    @Column (name = "weather_type")
    String weatherType;

    @Column (name = "time")
    Date time;

    @Column (name = "duration")
    int duration;

    @Column (name = "rating")
    int rating;

    @Column (name = "details")
    String details;

    @Column (name = "notify")
    Boolean notify;

    public int getWalkId() {
        return walkId;
    }

    public void setWalkId(int walkId) {
        this.walkId = walkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }
}
