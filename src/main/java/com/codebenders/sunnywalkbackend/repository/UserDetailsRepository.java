package com.codebenders.sunnywalkbackend.repository;

import com.codebenders.sunnywalkbackend.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
}
