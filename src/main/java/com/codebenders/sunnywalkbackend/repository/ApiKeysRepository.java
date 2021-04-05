package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.ApiKeys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeysRepository extends JpaRepository<ApiKeys, String> {
}
