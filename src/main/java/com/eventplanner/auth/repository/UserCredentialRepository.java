package com.eventplanner.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventplanner.auth.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer>{
    
    Optional<UserCredential> findByUsername(String username);
}
