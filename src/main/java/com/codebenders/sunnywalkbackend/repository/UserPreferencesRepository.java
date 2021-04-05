package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Integer> {
}
