package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRepository extends JpaRepository<Walk, Integer> {
}
