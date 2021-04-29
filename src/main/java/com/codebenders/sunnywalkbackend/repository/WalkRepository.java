package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface WalkRepository extends JpaRepository<Walk, Integer> {
    ArrayList<Walk> getWalkByNotify(Boolean notify);
}
