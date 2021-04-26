package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.User;
import com.codebenders.sunnywalkbackend.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    public UserSession getByUserId(Integer userId);
}
