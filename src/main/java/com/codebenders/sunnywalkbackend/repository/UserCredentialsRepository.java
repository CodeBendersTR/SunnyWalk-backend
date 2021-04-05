package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
}
