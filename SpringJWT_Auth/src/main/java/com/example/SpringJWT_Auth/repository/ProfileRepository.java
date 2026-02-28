package com.example.SpringJWT_Auth.repository;

import com.example.SpringJWT_Auth.entity.Profile;
import com.example.SpringJWT_Auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<
        Profile, Long> {
    boolean existsByUser(User user);
    Optional<Profile> findByUser(User user);
}
