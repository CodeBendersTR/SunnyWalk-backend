package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
}
