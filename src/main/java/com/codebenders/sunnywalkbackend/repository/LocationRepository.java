package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
