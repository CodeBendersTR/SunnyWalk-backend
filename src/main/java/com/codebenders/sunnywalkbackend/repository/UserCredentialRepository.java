package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    public UserCredential getByUserId(int userId);
}
