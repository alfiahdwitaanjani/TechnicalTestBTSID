package com.technicaltes.tesbts.id.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicaltes.tesbts.id.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
