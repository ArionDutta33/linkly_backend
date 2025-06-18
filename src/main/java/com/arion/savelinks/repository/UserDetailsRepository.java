package com.arion.savelinks.repository;

import com.arion.savelinks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
