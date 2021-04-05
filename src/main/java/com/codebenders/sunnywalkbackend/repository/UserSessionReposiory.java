package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionReposiory extends JpaRepository<UserSession, String> {
}
