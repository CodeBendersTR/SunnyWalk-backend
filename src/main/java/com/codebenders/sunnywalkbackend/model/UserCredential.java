package com.codebenders.sunnywalkbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "user_credential")
public class UserCredential {

    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "password_hash")
    String passwordHash;

    @Column(name = "role")
    String role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
