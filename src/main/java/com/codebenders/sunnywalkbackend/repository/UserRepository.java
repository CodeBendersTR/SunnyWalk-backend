package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByEmail(String email);
}