package com.sample.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.authservice.entity.UserData;

/**
 * @author KiranVellanki
 */
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);
    Optional<UserData> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
